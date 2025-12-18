/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 00:05:51 UTC+08:00
 ****************************************************/
package host.fairy.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
    
    private String code;
    private String message;
    
    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
    
    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    
    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }
}
