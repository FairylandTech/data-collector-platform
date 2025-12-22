/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-18 22:03:19 UTC+08:00
 ****************************************************/
package host.fairy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import host.fairy.entity.dto.user.UserCreateDTO;
import host.fairy.entity.dto.user.UserLoginDTO;
import host.fairy.entity.dto.user.UserQueryDTO;
import host.fairy.entity.dto.user.UserUpdateDTO;
import host.fairy.entity.vo.user.UserInfoVO;
import host.fairy.entity.vo.user.UserListVO;
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
import java.util.Objects;

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
    
    public static <R, T> PaginationResponse<T> fromPageHepler(List<T> list, List<R> source) {
        PageInfo<T> page = new PageInfo<>(list);
        PageInfo<R> sourcePageInfo = new PageInfo<>(source);
        
        return PaginationResponse.<T>builder()
                .data(page.getList() != null ? page.getList() : Collections.emptyList())
                .page(sourcePageInfo.getPageNum())
                .size(sourcePageInfo.getPageSize())
                .total(sourcePageInfo.getTotal())
                .totalPages(sourcePageInfo.getPages())
                .build();
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
        
        log.info("用户登录成功，用户名：{}", userLoginDTO.getUsername());
        String token = jwtAuthService.generateToken(user);
        log.info("生成JWT Token：{}", token);
        
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
        
        log.debug("转换UserCreateDTO为UserModel：{}", userCreateDTO.toUserModel());
        userMapper.insert(userCreateDTO.toUserModel());
        // 和用户组关联
        
        // 和角色关联
        
        // 返回用户信息
    }
    
    @Override
    public UserInfoVO getUserById(Long userId) {
        log.debug("根据用户ID获取用户信息，用户ID：{}", userId);
        UserModel user = this.userMapper.selectUserById(userId);
        log.debug("查询到的用户信息：{}", user);
        Objects.requireNonNull(user, "用户不存在");
        
        List<Long> userGroupIds = this.userGroupMapper.selectGroupIdsByUserId(userId);
        log.debug("查询用户所属的用户组: {}", userGroupIds);
        
        List<Long> userRoleIds = this.userRoleMapper.selectRoleIdsByUserId(userId);
        log.debug("查询用户所属的角色: {}", userRoleIds);
        
        return UserInfoVO.fromModel(user, userGroupIds, userRoleIds);
    }
    
    public PaginationResponse<UserListVO> getUserList(UserQueryDTO userQueryDTO) {
        log.debug("查询用户列表，查询条件：{}", userQueryDTO);
        
        PageHelper.startPage(userQueryDTO.getPage(), userQueryDTO.getSize());
        log.debug("分页参数设置，页码：{}，每页大小：{}", userQueryDTO.getPage(), userQueryDTO.getSize());
        
        log.debug("转换查询条件为MO对象：{}", userQueryDTO.toMO());
        List<UserModel> users = this.userMapper.select(userQueryDTO.toMO());
        log.debug("查询到的用户列表：{}", users);
        
        List<UserListVO> userListVOList = users.stream().map(UserListVO::fromModel).toList();
        log.debug("转换为UserListVO列表：{}", userListVOList);
        
        return fromPageHepler(userListVOList, users);
    }
    
    @Override
    public void updateUser(Long userId, UserUpdateDTO userUpdateDTO) {
        log.debug("更新用户信息，用户ID：{}，用户信息：{}", userId, userUpdateDTO);
        
        log.debug("转换UserUpdateDTO为UserModel：{}", userUpdateDTO.toUserModel());
        this.userMapper.update(userId, userUpdateDTO.toUserModel());
    }
}
