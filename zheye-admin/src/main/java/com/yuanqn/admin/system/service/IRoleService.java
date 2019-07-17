package com.yuanqn.admin.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanqn.admin.system.entity.Role;
import com.yuanqn.base.bean.QueryDTO;

import java.util.List;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 18:53
 */
public interface IRoleService extends IService<Role> {

    IPage<Role> findRoles(Role role, QueryDTO request);

    List<Role> findUserRole(String userName);

    Role findByName(String roleName);

    void createRole(Role role);

    void deleteRoles(String[] roleIds) throws Exception;

    void updateRole(Role role) throws Exception;
}
