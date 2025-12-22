/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-21 16:05:36 UTC+08:00
 ****************************************************/
package host.fairy.entity.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import host.fairy.model.user.UserModel;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
public class UserUpdateDTO {
    
    private String username;
    
    private String name;
    
    private String gender;
    
    private LocalDate birthday;
    
    private String phone;
    
    private String email;
    
    private List<Long> groups;
    
    private List<Long> roles;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime updatedAt = LocalDateTime.now(ZoneId.of("UTC+8"));
    
    public UserModel toUserModel() {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(this, userModel);
        return userModel;
    }
}
