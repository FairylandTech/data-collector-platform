/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 21:59:02 UTC+08:00
 ****************************************************/
package host.fairy.controller;

import host.fairy.entity.dto.user.UserCreateDTO;
import host.fairy.entity.dto.user.UserLoginDTO;
import host.fairy.entity.vo.user.UserLoginVO;
import host.fairy.fairylandfuture.http.Response;
import host.fairy.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    
    private final UserService userService;
    
    UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/login")
    public Response<UserLoginVO> login(@Valid @RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response) {
        UserLoginVO result = this.userService.login(userLoginDTO);
        response.setHeader("Authorization", result.getToken());
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return Response.success("登录成功", result);
    }
    
    @PostMapping()
    public Response<?> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO){
        log.info("创建用户：{}, 年龄: {}", userCreateDTO, userCreateDTO.getAge());
        return Response.success();
    }
}
