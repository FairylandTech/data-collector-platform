/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 03:07:19 UTC+08:00
 ****************************************************/
package host.fairy.service;

import host.fairy.model.user.UserModel;
import io.jsonwebtoken.Claims;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
public interface JWTAuthService {
    String generateToken(UserModel userModel);
    
    Claims parseToken(String token);
}
