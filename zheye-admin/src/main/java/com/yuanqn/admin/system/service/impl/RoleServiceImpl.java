package com.yuanqn.admin.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanqn.admin.system.entity.Role;
import com.yuanqn.admin.system.mapper.RoleMapper;
import com.yuanqn.admin.system.service.IRoleService;
import com.yuanqn.base.bean.QueryDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 18:55
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public IPage<Role> findRoles(Role role, QueryDTO request) {
        return null;
    }

    @Override
    public List<Role> findUserRole(String userName) {
        return baseMapper.findUserRole(userName);
    }

    @Override
    public Role findByName(String roleName) {
        return null;
    }

    @Override
    public void createRole(Role role) {

    }

    @Override
    public void deleteRoles(String[] roleIds) throws Exception {

    }

    @Override
    public void updateRole(Role role) throws Exception {

    }
}