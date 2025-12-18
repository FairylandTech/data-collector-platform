/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 12:29:36 UTC+08:00
 ****************************************************/
package host.fairy.enums;

import lombok.Getter;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Getter
public enum GenderEnum {
    FEMALE(0, "女"),
    MALE(1, "男"),
    
    UNKNOWN(2, "未知");
    
    private final int code;
    private final String description;
    
    GenderEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
