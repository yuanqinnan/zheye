package com.yuanqn.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanqn.admin.system.entity.UserRole;
import com.yuanqn.admin.system.mapper.UserRoleMapper;
import com.yuanqn.admin.system.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:yuanqinnan
 * @date: 2019/7/18 9:43
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {
}