/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 21:59:02 UTC+08:00
 ****************************************************/
package host.fairy.controller;

import host.fairy.entity.dto.UserLoginDTO;
import host.fairy.fairylandfuture.enums.http.ResponseStatusEnum;
import host.fairy.fairylandfuture.http.Response;
import host.fairy.service.UserLoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserLoginController {
    
    private final UserLoginService userLoginService;
    
    UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }
    
    @PostMapping("/login")
    public Response<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response) {
        log.info("进入 UserLoginController.login, 用户名: {}", userLoginDTO.getUsername());
        String token = this.userLoginService.login(userLoginDTO);
        if (token == null) {
            // TODO: 优化
            return Response.success(ResponseStatusEnum.UNAUTHORIZED.getCode(), "登录失败", null);
        }
        
        response.setHeader("Authorization", token);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return Response.success("登录成功");
    }
}
