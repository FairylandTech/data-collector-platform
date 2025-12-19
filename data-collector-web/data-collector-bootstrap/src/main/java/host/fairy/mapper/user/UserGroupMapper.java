/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-19 21:26:17 UTC+08:00
 ****************************************************/
package host.fairy.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Mapper
public interface UserGroupMapper {
    List<Long> selectGroupIdsByUserId(Long userId);
}
