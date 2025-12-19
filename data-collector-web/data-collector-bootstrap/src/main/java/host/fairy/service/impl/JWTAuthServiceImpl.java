/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 03:23:42 UTC+08:00
 ****************************************************/
package host.fairy.service.impl;

import host.fairy.enums.JWTClaimsKeyEnum;
import host.fairy.fairylandfuture.utils.authentication.JWTUtils;
import host.fairy.model.user.UserModel;
import host.fairy.properties.JwtProperties;
import host.fairy.service.JWTAuthService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Slf4j
@Service
public class JWTAuthServiceImpl implements JWTAuthService {
    
    private final JwtProperties jwtProperties;
    
    public JWTAuthServiceImpl(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }
    
    @Override
    public String generateToken(UserModel userModel) {
        HashMap<String, Object> claims = new HashMap<>() {
            {
                put(JWTClaimsKeyEnum.USER_ID.getName(), userModel.getId());
                put(JWTClaimsKeyEnum.USERNAME.getName(), userModel.getUsername());
            }
        };
        return JWTUtils.generateToken(this.jwtProperties.getSecretKey(), this.jwtProperties.getExpirationMillis(), claims);
    }
    
    @Override
    public Claims parseToken(String token) {
        return JWTUtils.parseToken(token, this.jwtProperties.getSecretKey());
    }
}
