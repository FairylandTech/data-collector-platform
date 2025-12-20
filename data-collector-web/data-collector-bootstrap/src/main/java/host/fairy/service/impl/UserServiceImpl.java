/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 22:03:19 UTC+08:00
 ****************************************************/
package host.fairy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import host.fairy.entity.dto.user.UserCreateDTO;
import host.fairy.entity.dto.user.UserLoginDTO;
import host.fairy.entity.dto.user.UserQueryDTO;
import host.fairy.entity.vo.user.UserInfoVO;
import host.fairy.entity.vo.user.UserLoginVO;
import host.fairy.fairylandfuture.common.web.response.PaginationResponse;
import host.fairy.fairylandfuture.exception.auth.AuthenticationException;
import host.fairy.fairylandfuture.exception.business.BusinessException;
import host.fairy.mapper.user.UserGroupMapper;
import host.fairy.mapper.user.UserMapper;
import host.fairy.mapper.user.UserRoleMapper;
import host.fairy.model.user.UserModel;
import host.fairy.service.JWTAuthService;
import host.fairy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;
    
    private final UserGroupMapper userGroupMapper;
    
    private final UserRoleMapper userRoleMapper;
    
    private final JWTAuthService jwtAuthService;
    
    public UserServiceImpl(UserMapper userMapper, UserGroupMapper userGroupMapper, UserRoleMapper userRoleMapper, JWTAuthService jwtAuthService) {
        this.userMapper = userMapper;
        this.userGroupMapper = userGroupMapper;
        this.userRoleMapper = userRoleMapper;
        this.jwtAuthService = jwtAuthService;
    }
    
    @Override
    public UserLoginVO login(UserLoginDTO userLoginDTO) {
        log.info("用户登录，用户名：{}", userLoginDTO.getUsername());
        // TODO: 校验验证码
        
        // TODO: 密码加密比较
        
        UserModel user = userMapper.selectUserByUsername(userLoginDTO.getUsername());
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
        UserModel existingUser = userMapper.selectUserByUsername(userCreateDTO.getUsername());
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
    
    @Override
    public UserInfoVO getUserById(Long userId) {
        UserModel user = this.userMapper.selectUserById(userId);
        if (user == null) {
            log.error("用户不存在，用户ID：{}", userId);
            throw new BusinessException("用户不存在");
        }
        List<Long> userGroupIds = this.userGroupMapper.selectGroupIdsByUserId(userId);
        List<Long> userRoleIds = this.userRoleMapper.selectRoleIdsByUserId(userId);
        return UserInfoVO.fromModel(user, userGroupIds, userRoleIds);
    }
    
    public PaginationResponse<UserModel> getUserList(UserQueryDTO userQueryDTO) {
        log.debug("查询用户列表，查询条件：{}", userQueryDTO);
        PageHelper.startPage(userQueryDTO.getPage(), userQueryDTO.getSize());
        log.debug("分页参数设置，页码：{}，每页大小：{}", userQueryDTO.getPage(), userQueryDTO.getSize());
        log.debug("转换查询条件为MO对象：{}", userQueryDTO.toMO());
        List<UserModel> users = this.userMapper.select(userQueryDTO.toMO());
        log.debug("查询到的用户列表：{}", users);
        return fromPageHepler(users);
    }
    
    public static <E> Page<E> convertToPage(List<E> list) {
        return (Page<E>) list;
    }
    
    public static <E> PaginationResponse<E> fromPageHepler(List<E> list) {
        Page<E> page = convertToPage(list);
        
        if (page == null) {
            return PaginationResponse.<E>builder()
                    .data(Collections.emptyList())
                    .page(0)
                    .size(0)
                    .total(0)
                    .totalPages(0)
                    .build();
        }
        
        return PaginationResponse.<E>builder()
                .data(page.getResult() != null ? page.getResult() : Collections.emptyList())
                .page(page.getPageNum())
                .size(page.getPageSize())
                .total(page.getTotal())
                .totalPages(page.getPages())
                .build();
    }
}
