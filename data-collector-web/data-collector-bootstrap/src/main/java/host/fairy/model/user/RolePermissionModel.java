/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 13:03:18 UTC+08:00
 ****************************************************/
package host.fairy.model.user;

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
public class RolePermissionModel extends ModelBase {
    
    private Long roleId;
    
    private Long permissionId;
}
