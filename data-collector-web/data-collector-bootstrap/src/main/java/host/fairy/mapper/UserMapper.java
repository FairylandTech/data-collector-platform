/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 22:51:10 UTC+08:00
 ****************************************************/
package host.fairy.mapper;

import host.fairy.model.user.UserModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    
    UserModel findByUsername(String username);
    
    void insert(UserModel user);
}
