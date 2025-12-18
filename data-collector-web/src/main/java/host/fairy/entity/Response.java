/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 11:15:18 UTC+08:00
 ****************************************************/
package host.fairy.entity;

import host.fairy.enums.ResponseStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class Response<T> {
    
    private int code;
    private String message;
    private T data;
    
    public static Response<Void> success() {
        return new Response<>(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMessage(), null);
    }
    
    public static <T> Response<T> success(int code) {
        ResponseStatusEnum status = ResponseStatusEnum.findByCodeOrDefault(code, ResponseStatusEnum.SUCCESS);
        return new Response<>(status.getCode(), status.getMessage(), null);
    }
    
    public static <T> Response<T> success(T data) {
        return new Response<>(ResponseStatusEnum.SUCCESS.getCode(), ResponseStatusEnum.SUCCESS.getMessage(), data);
    }
    
    public static <T> Response<T> success(String message, T data) {
        return new Response<>(ResponseStatusEnum.SUCCESS.getCode(), message, data);
    }
    
    public static <T> Response<T> success(int code, String message, T data) {
        return new Response<>(code, message, data);
    }
    
    public static Response<Void> failure(String message) {
        return new Response<>(ResponseStatusEnum.ERROR.getCode(), message, null);
    }
    
    public static Response<Void> failure(int code, String message) {
        return new Response<>(code, message, null);
    }
    
    public static <T> Response<T> failure(int code, String message, T data) {
        return new Response<>(code, message, data);
    }
}
