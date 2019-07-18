package com.yuanqn.admin.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuanqn.admin.system.entity.UserConfig;

/**
 * @author:yuanqinnan
 * @date: 2019/7/18 9:56
 */
public interface IUserConfigService  extends IService<UserConfig> {
    /**
     * 生成用户默认个性化配置
     *
     * @param userId 用户 ID
     */
    void initDefaultUserConfig(String userId);

    /**
     * 通过用户 ID 获取前端系统个性化配置
     *
     * @param userId 用户 ID
     * @return 前端系统个性化配置
     */
    UserConfig findByUserId(String userId);
}
