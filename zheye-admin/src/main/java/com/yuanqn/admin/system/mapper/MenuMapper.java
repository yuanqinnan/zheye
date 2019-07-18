package com.yuanqn.admin.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuanqn.admin.system.entity.Menu;

import java.util.List;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 19:10
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> findUserPermissions(String username);
}