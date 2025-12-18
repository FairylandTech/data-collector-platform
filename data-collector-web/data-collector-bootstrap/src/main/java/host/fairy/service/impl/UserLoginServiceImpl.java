/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 22:03:19 UTC+08:00
 ****************************************************/
package host.fairy.service.impl;

import host.fairy.entity.dto.UserLoginDTO;
import host.fairy.exception.AuthenticationException;
import host.fairy.mapper.UserMapper;
import host.fairy.model.user.UserModel;
import host.fairy.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Slf4j
@Service
public class UserLoginServiceImpl implements UserLoginService {
    
    private final UserMapper userMapper;
    
    public UserLoginServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    @Override
    public String login(UserLoginDTO userLoginDTO) {
        // TOOD: 校验验证码
        
        // 校验用户名和密码
        UserModel user = userMapper.findByUsername(userLoginDTO.getUsername());
        
        if (user == null || !user.getPassword().equals(userLoginDTO.getPassword())) {
            log.error("用户登录失败，用户名或密码错误，用户名：{}", userLoginDTO.getUsername());
            throw new AuthenticationException("用户名或密码错误");
        }
        
        
        return "user";
    }
}
