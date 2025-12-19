/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 21:59:02 UTC+08:00
 ****************************************************/
package host.fairy.controller;

import host.fairy.entity.dto.UserLoginDTO;
import host.fairy.entity.vo.UserLoginVO;
import host.fairy.fairylandfuture.http.Response;
import host.fairy.service.UserLoginService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Response<UserLoginVO> login(@Valid @RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response) {
        UserLoginVO result = this.userLoginService.login(userLoginDTO);
        response.setHeader("Authorization", result.getToken());
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return Response.success("登录成功", result);
    }
}
