/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 22:51:10 UTC+08:00
 ****************************************************/
package host.fairy.mapper.user;

import host.fairy.entity.mo.user.UserQueryMO;
import host.fairy.model.user.UserModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    Integer insert(UserModel user);
    
    Integer update(UserModel user);
    
    Integer deleteById(Long id);
    
    List<UserModel> select(UserQueryMO user);
    
    UserModel selectUserById(Long id);
    
    UserModel selectUserByUsername(String username);
}
