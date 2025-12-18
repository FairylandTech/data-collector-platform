/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 23:57:56 UTC+08:00
 ****************************************************/
package host.fairy.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

/**
 * JWT Utils
 *
 * @author Lionel Johnson
 * @version 1.0
 */
public class JwtUtils {
    
    /**
     * Generate JWT Token
     *
     * @param sercret   Secret key
     * @param ttlSecond Token time to live in seconds
     * @param claims    Claims
     * @return JWT Token
     */
    public static String generateToken(String sercret, Long ttlSecond, Map<String, Object> claims) {
        
        long expiration = LocalDateTime.now().plusSeconds(ttlSecond).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        
        JwtBuilder jwtBuilder = Jwts.builder()
                .claims(claims)
                .signWith(Keys.hmacShaKeyFor(sercret.getBytes(StandardCharsets.UTF_8)))
                .expiration(new Date(expiration));
        
        return jwtBuilder.compact();
    }
    
    /**
     * Parse JWT Token
     *
     * @param token  JWT Token
     * @param secret Secret key
     * @return Claims
     */
    public static Claims parseToken(String token, String secret) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    
    public static void main(String[] args) {
//        SecretKey secretKey = Jwts.SIG.HS256.key().build();
//        System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImV4cCI6MTc1NjQ2MTM5Nn0.LT1xnfOMnHf9I1_8ak6ofRE1EIY0eAjvnIcXu41vN2s";
        Claims claims = parseToken(token, "7QXqrqtaWVwOAV0ACT4sDRvmIDXvv6XQqymI/iabs/U=");
        System.out.println(claims.toString());
        Integer id = claims.get("id", Integer.class);
        String string = claims.get("username", String.class);
        
        System.out.println(id);
        System.out.println(string);
    }
}
