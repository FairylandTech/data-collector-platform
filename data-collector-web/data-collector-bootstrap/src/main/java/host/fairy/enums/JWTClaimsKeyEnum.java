/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 03:19:50 UTC+08:00
 ****************************************************/
package host.fairy.enums;

import lombok.Getter;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Getter
public enum JWTClaimsKeyEnum {
    USER_ID("userID"),
    USERNAME("username");
    
    private final String name;
    
    JWTClaimsKeyEnum(String name) {
        this.name = name;
    }
}
