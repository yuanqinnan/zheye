package com.yuanqn.admin.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuanqn.admin.system.entity.UserConfig;
import com.yuanqn.admin.system.mapper.UserConfigMapper;
import com.yuanqn.admin.system.service.IUserConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:yuanqinnan
 * @date: 2019/7/18 9:59
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserConfigServiceImpl extends ServiceImpl<UserConfigMapper, UserConfig> implements IUserConfigService {

    @Override
    public void initDefaultUserConfig(String userId) {
        UserConfig userConfig = new UserConfig();
        userConfig.setUserId(Long.valueOf(userId));
        userConfig.setColor(UserConfig.DEFAULT_COLOR);
        userConfig.setFixHeader(UserConfig.DEFAULT_FIX_HEADER);
        userConfig.setFixSidebar(UserConfig.DEFAULT_FIX_SIDEBAR);
        userConfig.setLayout(UserConfig.DEFAULT_LAYOUT);
        userConfig.setTheme(UserConfig.DEFAULT_THEME);
        userConfig.setMultiPage(UserConfig.DEFAULT_MULTI_PAGE);
        baseMapper.insert(userConfig);
    }

    @Override
    public UserConfig findByUserId(String userId) {
        return baseMapper.selectOne(new LambdaQueryWrapper<UserConfig>().eq(UserConfig::getUserId, userId));
    }
}