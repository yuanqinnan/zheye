package com.yuanqn.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanqn.admin.common.enums.UserSexEnum;
import com.yuanqn.admin.common.enums.UserStatusEnum;
import com.yuanqn.admin.system.entity.User;
import com.yuanqn.admin.system.entity.UserRole;
import com.yuanqn.admin.system.manager.UserManager;
import com.yuanqn.admin.system.mapper.UserMapper;
import com.yuanqn.admin.system.mapper.UserRoleMapper;
import com.yuanqn.admin.system.service.ICacheService;
import com.yuanqn.admin.system.service.IUserConfigService;
import com.yuanqn.admin.system.service.IUserRoleService;
import com.yuanqn.admin.system.service.IUserService;
import com.yuanqn.admin.common.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author yuanqn
 * @date 2019/7/13 23:34
 */

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private ICacheService cacheService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private IUserConfigService userConfigService;

    @Autowired
    private UserManager userManager;

    @Override
    public User findByName(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    @Transactional
    public void createUser(User user) {
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        user.setStatus(User.DEFAULT_STATUS);
        user.setPassword(MD5Util.encrypt(user.getUsername(), User.DEFAULT_PASSWORD));
        save(user);
    }

    @Override
    @Transactional
    public void updateLoginTime(String username) throws Exception {
        User user = new User();
        user.setLastLoginTime(new Date());

        this.baseMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, username));

        // 重新将用户信息加载到 redis中
        cacheService.saveUser(username);
    }

    @Override
    public void register(String username, String password) throws Exception {
        User user = new User();
        user.setPassword(MD5Util.encrypt(username, password));
        user.setUsername(username);
        user.setCreateTime(new Date());
        user.setStatus(UserStatusEnum.EFFECTIVITY.getCode());
        user.setSex(UserSexEnum.MAN.getCode());
        this.save(user);

        UserRole ur = new UserRole();
        ur.setUserId(user.getUserId());
        ur.setRoleId(2L); // 注册用户角色 ID
        this.userRoleMapper.insert(ur);

        // 创建用户默认的个性化配置
        userConfigService.initDefaultUserConfig(String.valueOf(user.getUserId()));
        // 将用户相关信息保存到 Redis中
        userManager.loadUserRedisCache(user);
    }
}
