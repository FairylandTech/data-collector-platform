/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 22:02:39 UTC+08:00
 ****************************************************/
package host.fairy.service;

import host.fairy.entity.dto.user.UserCreateDTO;
import host.fairy.entity.dto.user.UserLoginDTO;
import host.fairy.entity.vo.user.UserInfoVO;
import host.fairy.entity.vo.user.UserLoginVO;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
public interface UserService {
    UserLoginVO login(UserLoginDTO userLoginDTO);
    
    void createUser(UserCreateDTO userCreateDTO);
    
    UserInfoVO getUserById(long userId);
}
