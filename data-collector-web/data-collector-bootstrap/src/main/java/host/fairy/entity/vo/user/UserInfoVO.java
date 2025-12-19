/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 20:53:45 UTC+08:00
 ****************************************************/
package host.fairy.entity.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import host.fairy.model.user.UserModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
public class UserInfoVO {
    private Long id;
    
    private String username;
    
    private String name;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC+8")
    private LocalDate birthday;
    
    private String phone;
    
    private String email;
    
    private List<Long> groups;
    
    private List<Long> roles;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime createdAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime updatedAt;
    
    private boolean deleted;
    
    public static UserInfoVO fromModel(UserModel user, List<Long> userGroupIds, List<Long> userRoleIds) {
        UserInfoVO vo = new UserInfoVO();
        BeanUtils.copyProperties(user, vo);
        vo.setGroups(userGroupIds);
        vo.setRoles(userRoleIds);
        return vo;
    }
}
