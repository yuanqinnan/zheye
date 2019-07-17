package com.yuanqn.admin.system.service.impl;

import com.pugwoo.wooutils.redis.RedisHelper;
import com.yuanqn.admin.system.entity.Menu;
import com.yuanqn.admin.system.entity.Role;
import com.yuanqn.admin.system.entity.User;
import com.yuanqn.admin.system.service.ICacheService;
import org.apache.catalina.startup.UserConfig;
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


    @Override
    public void testConnect() throws Exception {

    }

    @Override
    public User getUser(String username) throws Exception {
        return null;
    }

    @Override
    public List<Role> getRoles(String username) throws Exception {
        return null;
    }

    @Override
    public List<Menu> getPermissions(String username) throws Exception {
        return null;
    }

    @Override
    public UserConfig getUserConfig(String userId) throws Exception {
        return null;
    }

    @Override
    public void saveUser(User user) throws Exception {

    }

    @Override
    public void saveUser(String username) throws Exception {

    }

    @Override
    public void saveRoles(String username) throws Exception {

    }

    @Override
    public void savePermissions(String username) throws Exception {

    }

    @Override
    public void saveUserConfigs(String userId) throws Exception {

    }

    @Override
    public void deleteUser(String username) throws Exception {

    }

    @Override
    public void deleteRoles(String username) throws Exception {

    }

    @Override
    public void deletePermissions(String username) throws Exception {

    }

    @Override
    public void deleteUserConfigs(String userId) throws Exception {

    }
}