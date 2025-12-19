/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 22:51:10 UTC+08:00
 ****************************************************/
package host.fairy.mapper.user;

import host.fairy.model.user.UserModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    
    UserModel selectUserByUsername(String username);
    
    UserModel selectUserById(Long id);
    
    void insert(UserModel user);
}
