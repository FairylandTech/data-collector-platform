/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 12:19:19 UTC+08:00
 ****************************************************/
package host.fairy.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import host.fairy.fairylandfuture.enums.GenderEnum;
import host.fairy.fairylandfuture.model.ModelBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserModel extends ModelBase {
    
    private String username;
    
    @JsonIgnore
    private String password;
    
    private String name;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthday;
    
    private GenderEnum gender;
    
    private String phone;
    
    private String email;
}
