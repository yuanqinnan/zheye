package com.yuanqn.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanqn.admin.system.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yuanqn
 * @date 2019/7/13 23:32
 */

public interface IUserService extends IService<User> {

    /**
     * 通过用户名查找用户
     *
     * @param username username
     * @return user
     */
    User findByName(String username);

    /**
     * 新增用户
     *
     * @param user user
     */
    void createUser(User user);

    /**
     * 更新登录时间
     *
     * @param 
       @return 
    */
    void updateLoginTime(String username) throws Exception;


    /**
     * 注册用户
     *
     * @param 
       @return 
    */
    void register(String username, String password) throws Exception;

}
