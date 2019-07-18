package com.yuanqn.admin.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanqn.admin.system.entity.Menu;
import com.yuanqn.admin.system.mapper.MenuMapper;
import com.yuanqn.admin.system.service.IMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 19:09
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Override
    public List<Menu> findUserPermissions(String username) {
        return baseMapper.findUserPermissions(username);
    }

    @Override
    public List<Menu> findUserMenus(String username) {
        return null;
    }

    @Override
    public Map<String, Object> findMenus(Menu menu) {
        return null;
    }

    @Override
    public List<Menu> findMenuList(Menu menu) {
        return null;
    }

    @Override
    public void createMenu(Menu menu) {

    }

    @Override
    public void updateMenu(Menu menu) throws Exception {

    }

    @Override
    public void deleteMenus(String[] menuIds) throws Exception {

    }
}