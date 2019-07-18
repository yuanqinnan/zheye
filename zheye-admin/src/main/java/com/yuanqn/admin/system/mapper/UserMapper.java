package com.yuanqn.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuanqn.admin.system.entity.User;

/**
 * @author yuanqn
 * @date 2019/7/13 23:31
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取单个用户详情
     *
     * @param username 用户名
     * @return 用户信息
     */
    User findDetail(String username);
}
