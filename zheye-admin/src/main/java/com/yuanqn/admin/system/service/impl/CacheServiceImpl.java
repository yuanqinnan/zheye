package com.yuanqn.admin.system.service.impl;

import com.pugwoo.wooutils.redis.RedisHelper;
import com.yuanqn.admin.common.config.Constant;
import com.yuanqn.admin.system.entity.Menu;
import com.yuanqn.admin.system.entity.Role;
import com.yuanqn.admin.system.entity.User;
import com.yuanqn.admin.system.entity.UserConfig;
import com.yuanqn.admin.system.mapper.UserMapper;
import com.yuanqn.admin.system.service.ICacheService;
import com.yuanqn.admin.system.service.IMenuService;
import com.yuanqn.admin.system.service.IRoleService;
import com.yuanqn.admin.system.service.IUserConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 18:52
 */
@Service("cacheService")
public class CacheServiceImpl implements ICacheService {
    @Autowired
    private RedisHelper redisHelper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserConfigService userConfigService;


    @Override
    public boolean testConnect() throws Exception {
        return redisHelper.setObject("test", 100, "test");
    }

    @Override
    public User getUser(String username) throws Exception {
        return redisHelper.getObject(Constant.USER_CACHE_PREFIX + username, User.class);
    }

    @Override
    public List<Role> getRoles(String username) throws Exception {
        return redisHelper.getObject(Constant.USER_ROLE_CACHE_PREFIX + username, List.class, Role.class);
    }

    @Override
    public List<Menu> getPermissions(String username) throws Exception {
        return redisHelper.getObject(Constant.USER_PERMISSION_CACHE_PREFIX + username, List.class, Menu.class);
    }

    @Override
    public UserConfig getUserConfig(String userId) throws Exception {
        return this.redisHelper.getObject(Constant.USER_CONFIG_CACHE_PREFIX + userId, UserConfig.class);
    }

    @Override
    public void saveUser(User user) throws Exception {
        this.deleteUser(user.getUsername());
        redisHelper.setObject(Constant.USER_CACHE_PREFIX + user.getUsername(), 3600 * 24, user);
    }

    @Override
    public void saveUser(String username) throws Exception {
        User user = userMapper.findDetail(username);
        this.deleteUser(username);
        redisHelper.setObject(Constant.REDIS_NAMESPACE + username, 3600 * 24, user);
    }

    @Override
    public void saveRoles(String username) throws Exception {
        List<Role> roleList = this.roleService.findUserRole(username);
        if (!roleList.isEmpty()) {
            this.deleteRoles(username);
            redisHelper.setObject(Constant.USER_ROLE_CACHE_PREFIX + username, 3600 * 24, roleList);
        }
    }

    @Override
    public void savePermissions(String username) throws Exception {
        List<Menu> permissionList = this.menuService.findUserPermissions(username);
        if (!permissionList.isEmpty()) {
            redisHelper.setObject(Constant.USER_PERMISSION_CACHE_PREFIX + username, 3600 * 24, permissionList);
        }
    }

    @Override
    public void saveUserConfigs(String userId) throws Exception {
        UserConfig userConfig = this.userConfigService.findByUserId(userId);
        if (userConfig != null) {
            this.deleteUserConfigs(userId);
            redisHelper.setObject(Constant.USER_CONFIG_CACHE_PREFIX + userId, 3600 * 24, userConfig);
        }
    }

    @Override
    public void deleteUser(String username) throws Exception {
        username = username.toLowerCase();
        redisHelper.remove(Constant.USER_CACHE_PREFIX + username);
    }

    @Override
    public void deleteRoles(String username) throws Exception {
        username = username.toLowerCase();
        redisHelper.remove(Constant.USER_ROLE_CACHE_PREFIX + username);
    }

    @Override
    public void deletePermissions(String username) throws Exception {
        username = username.toLowerCase();
        redisHelper.remove(Constant.USER_PERMISSION_CACHE_PREFIX + username);
    }

    @Override
    public void deleteUserConfigs(String userId) throws Exception {
        redisHelper.remove(Constant.USER_CONFIG_CACHE_PREFIX + userId);
    }
}