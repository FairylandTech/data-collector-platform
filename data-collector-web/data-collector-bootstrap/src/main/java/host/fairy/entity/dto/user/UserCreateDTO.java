/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 12:29:32 UTC+08:00
 ****************************************************/
package host.fairy.entity.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import host.fairy.model.user.UserModel;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
public class UserCreateDTO {
    
    private String username;
    
    private String password;
    
    private String name;
    
    private String gender;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;
    
    private String phone;
    
    private String email;
    
    private List<Long> groups;
    
    private List<Long> roles;
    
    public Integer getAge() {
        if (this.birthday == null) {
            return null;
        }
        
        if (this.birthday.isAfter(LocalDate.now())) {
            return 0;
        }
        
        return Period.between(this.birthday, LocalDate.now()).getYears();
    }
    
    public UserModel toUserModel() {
        UserModel model = new UserModel();
        model.setUsername(this.username);
        model.setPassword(this.password);
        model.setName(this.name);
        model.setGender(this.gender);
        model.setBirthday(this.birthday);
        model.setPhone(this.phone);
        model.setEmail(this.email);
        return model;
    }
}
