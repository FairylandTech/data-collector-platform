/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 03:12:41 UTC+08:00
 ****************************************************/
package host.fairy.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {
    
    private String secretKey;
    
    private Long expirationMillis;
}
