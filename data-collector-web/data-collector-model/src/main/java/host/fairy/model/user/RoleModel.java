/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 12:45:45 UTC+08:00
 ****************************************************/
package host.fairy.model.user;


import host.fairy.fairylandfuture.model.ModelBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class RoleModel extends ModelBase {
    
    private String name;
    
    private String code;
    
    private String description;
    
    private boolean builtin;
}
