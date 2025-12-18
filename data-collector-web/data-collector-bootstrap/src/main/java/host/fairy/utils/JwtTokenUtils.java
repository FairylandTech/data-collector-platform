/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 00:15:43 UTC+08:00
 ****************************************************/
package host.fairy.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Component
public class JwtTokenUtils {
    
    @Value("${jwt.secret:defaultSecretKeyThatShouldBeAtLeast32CharactersLong}")
    private String jwtSecret;
    
    @Value("${jwt.expiration:86400000}")
    private long jwtExpirationMs;
    
    @Value("${jwt.issuer:demo-app}")
    private String issuer;
    
    private SecretKey getSigningKey() {
        // 确保密钥长度至少32个字符
        if (jwtSecret.length() < 32) {
            // 如果不够长，进行填充
            StringBuilder sb = new StringBuilder(jwtSecret);
            while (sb.length() < 32) {
                sb.append("0");
            }
            jwtSecret = sb.toString();
        }
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    
    /**
     * 生成JWT Token
     */
    public String generateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    
    /**
     * 从Token中获取用户名
     */
    public String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }
    
    /**
     * 从Token中获取用户ID
     */
    public Long getUserIdFromToken(String token) {
        Object userId = getAllClaimsFromToken(token).get("userId");
        if (userId instanceof Number) {
            return ((Number) userId).longValue();
        }
        return null;
    }
    
    /**
     * 从Token中获取角色
     */
    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        return (List<String>) getAllClaimsFromToken(token).get("roles");
    }
    
    /**
     * 获取所有Claims
     */
    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            // Token已过期，但仍然可以获取claims
            return e.getClaims();
        }
    }
    
    /**
     * 验证Token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new RuntimeException("Token已过期", e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException("不支持的Token格式", e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException("Token格式错误", e);
        } catch (SecurityException e) {
            throw new RuntimeException("Token签名验证失败", e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Token参数异常", e);
        }
    }
    
    /**
     * 获取Token过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }
    
    /**
     * 检查Token是否过期
     */
    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    
    /**
     * 刷新Token
     */
    public String refreshToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(new Date());
        claims.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs));
        
        return Jwts.builder()
                .setClaims(claims)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }
}
