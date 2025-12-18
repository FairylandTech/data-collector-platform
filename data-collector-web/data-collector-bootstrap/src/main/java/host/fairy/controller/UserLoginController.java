/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 21:59:02 UTC+08:00
 ****************************************************/
package host.fairy.controller;

import host.fairy.entity.Response;
import host.fairy.entity.dto.UserLoginDTO;
import host.fairy.service.UserLoginService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@RestController
@RequestMapping("/api")
public class UserLoginController {
    
    private final UserLoginService userLoginService;
    
    UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }
    
    @GetMapping("/login")
    public Response<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        String token = this.userLoginService.login(userLoginDTO);
        
        return Response.success("登录成功");
    }
}
