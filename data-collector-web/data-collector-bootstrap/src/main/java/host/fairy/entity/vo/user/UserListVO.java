/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-21 04:17:51 UTC+08:00
 ****************************************************/
package host.fairy.entity.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import host.fairy.model.user.UserModel;
import host.fairy.utils.EntityFieldConverterUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
public class UserListVO {
    private Long id;
    
    private String username;
    
    private String name;
    
    private String gender;
    
    private Integer age;
    
    private String phone;
    
    private String email;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime createdAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime updatedAt;
    
    private Boolean deleted;
    
    public static UserListVO fromModel(UserModel user) {
        UserListVO vo = new UserListVO();
        BeanUtils.copyProperties(user, vo);
        if (user.getBirthday() != null) {
            int age = LocalDateTime.now().getYear() - user.getBirthday().getYear();
            vo.setAge(age);
        } else {
            vo.setAge(null);
        }
        vo.setGender(EntityFieldConverterUtils.convertUserGender(vo.getGender()));
        return vo;
    }
}
