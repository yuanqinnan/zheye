package com.yuanqn.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanqn.admin.system.entity.User;
import com.yuanqn.admin.system.mapper.UserMapper;
import com.yuanqn.admin.system.service.IUserService;
import com.yuanqn.admin.common.utils.MD5Util;
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

    @Override
    @Transactional
    public void createUser(User user) {
        user.setCreateTime(new Date());
        user.setModifyTime(new Date());
        user.setStatus(User.DEFAULT_STATUS);
        user.setPassword(MD5Util.encrypt(user.getUsername(), User.DEFAULT_PASSWORD));
        save(user);
    }
}
