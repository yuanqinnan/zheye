package com.yuanqn.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuanqn.admin.system.entity.Role;

import java.util.List;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 18:55
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> findUserRole(String userName);
}