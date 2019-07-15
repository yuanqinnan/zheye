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
     * 新增用户
     *
     * @param user user
     */
    void createUser(User user);

}