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
import host.fairy.entity.dto.user.UserQueryDTO;
import host.fairy.entity.vo.user.UserInfoVO;
import host.fairy.entity.vo.user.UserLoginVO;
import host.fairy.fairylandfuture.common.web.response.PaginationResponse;
import host.fairy.fairylandfuture.common.web.response.Response;
import host.fairy.model.user.UserModel;
import host.fairy.service.UserService;
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
public class UserController {
    
    private final UserService userService;
    
    UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/login")
    public Response<UserLoginVO> login(@Valid UserLoginDTO userLoginDTO, HttpServletResponse response) {
        UserLoginVO result = this.userService.login(userLoginDTO);
        response.setHeader("Authorization", result.getToken());
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        return Response.success("登录成功", result);
    }
    
    @GetMapping("")
    public Response<PaginationResponse<UserModel>> getUserList(UserQueryDTO userQueryDTO) {
        log.debug("获取用户列表");
        return Response.success(this.userService.getUserList(userQueryDTO));
    }
    
    @PostMapping()
    public Response<?> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        log.debug("创建用户：{}, 年龄: {}", userCreateDTO, userCreateDTO.getAge());
        return Response.success();
    }
    
    @GetMapping("/{userId}")
    public Response<UserInfoVO> getProfile(@PathVariable Long userId) {
        log.debug("获取用户信息，用户ID：{}", userId);
        return Response.success(this.userService.getUserById(userId));
    }
}
