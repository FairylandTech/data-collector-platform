/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 22:03:19 UTC+08:00
 ****************************************************/
package host.fairy.service.impl;

import host.fairy.entity.dto.user.UserCreateDTO;
import host.fairy.entity.dto.user.UserLoginDTO;
import host.fairy.entity.vo.user.UserLoginVO;
import host.fairy.fairylandfuture.exception.auth.AuthenticationException;
import host.fairy.fairylandfuture.exception.business.BusinessException;
import host.fairy.mapper.UserMapper;
import host.fairy.model.user.UserModel;
import host.fairy.service.JWTAuthService;
import host.fairy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;
    
    private final JWTAuthService jwtAuthService;
    
    public UserServiceImpl(UserMapper userMapper, JWTAuthService jwtAuthService) {
        this.userMapper = userMapper;
        this.jwtAuthService = jwtAuthService;
    }
    
    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        log.info("用户登录，用户名：{}", userLoginDTO.getUsername());
        // TODO: 校验验证码
        
        // TODO: 密码加密比较
        
        UserModel user = userMapper.findByUsername(userLoginDTO.getUsername());
        log.info("查询到的用户信息：{}", user);
        if (user == null || !user.getPassword().equals(userLoginDTO.getPassword())) {
            log.error("用户登录失败，用户名或密码错误，用户名：{}", userLoginDTO.getUsername());
            throw new AuthenticationException("用户名或密码错误");
        }
        
        String token = jwtAuthService.generateToken(user);
        
        return UserLoginVO.fromUserModel(user, token);
    }
    
    @Override
    public void createUser(UserCreateDTO userCreateDTO) {
        log.info("创建用户，用户名：{}", userCreateDTO.getUsername());
        
        // 校验用户名是否已存在
        UserModel existingUser = userMapper.findByUsername(userCreateDTO.getUsername());
        if (existingUser != null) {
            log.error("创建用户失败，用户名已存在，用户名：{}", userCreateDTO.getUsername());
            throw new BusinessException("用户名已存在");
        }
        // TODO: 密码加密存储
        
        // 创建用户
        userMapper.insert(userCreateDTO.toUserModel());
        // 和用户组关联
        
        // 和角色关联
        
        // 返回用户信息
    }

}
