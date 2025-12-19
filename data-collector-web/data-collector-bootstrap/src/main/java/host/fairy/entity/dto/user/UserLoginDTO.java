/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 23:26:02 UTC+08:00
 ****************************************************/
package host.fairy.entity.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
public class UserLoginDTO {
    
    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 20, message = "用户名长度必须在4-20个字符之间")
    @Pattern(regexp = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$",
            message = "用户名只能包含中文、字母、数字和下划线")
    private String username;
    
    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 20, message = "密码长度必须在6-20个字符之间")
//    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).*$",
//            message = "密码必须包含至少一个字母和一个数字")
    private String password;
    
    @NotBlank(message = "验证码不能为空")
    @Size(min = 4, max = 6, message = "验证码长度必须在4-6个字符之间")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "验证码只能包含字母和数字")
    private String captcha;
}
