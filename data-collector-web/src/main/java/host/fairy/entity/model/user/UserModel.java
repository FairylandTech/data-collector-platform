/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 12:19:19 UTC+08:00
 ****************************************************/
package host.fairy.entity.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import host.fairy.entity.model.ModelBase;
import host.fairy.enums.GenderEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserModel extends ModelBase {
    
    private String username;
    
    @JsonIgnore
    private String password;
    
    private String name;
    
    private LocalDateTime birthday;
    
    private GenderEnum gender;
    
    private String phone;
    
    private String email;
}
