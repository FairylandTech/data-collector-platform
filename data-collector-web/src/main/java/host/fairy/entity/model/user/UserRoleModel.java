/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 13:01:28 UTC+08:00
 ****************************************************/
package host.fairy.entity.model.user;

import host.fairy.entity.model.ModelBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class UserRoleModel extends ModelBase {
    
    private Long userId;
    
    private Long roleId;
}
