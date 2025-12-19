/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 11:51:37 UTC+08:00
 ****************************************************/
package host.fairy.entity.vo;

import host.fairy.model.user.UserModel;
import lombok.Data;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
public class UserLoginVO {
    private Long id;
    
    private String username;
    
    private String token;
    
    public static UserLoginVO fromUserModel(UserModel user, String token) {
        UserLoginVO vo = new UserLoginVO();
        vo.setId(user.getId());
        vo.setUsername(user.getUsername());
        vo.setToken(token);
        return vo;
    }
}
