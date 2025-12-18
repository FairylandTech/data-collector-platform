/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 12:55:27 UTC+08:00
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
public class PermissionModel extends ModelBase {
    
    private String code;
    
    private String name;
    
    private String description;
    
    private Long parentId;
    
    private String url;
}
