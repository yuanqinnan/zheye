package com.yuanqn.admin.common.config;

/**
 * @author yuanqn
 * @date 2019/7/14 10:50
 */
public interface Constant {

    String REDIS_NAMESPACE = "zheye-admin:";

    String CODE_PREFIX = "captcha:";

    // user缓存前缀
    String USER_CACHE_PREFIX = REDIS_NAMESPACE + "cache-user:";
    // user角色缓存前缀
    String USER_ROLE_CACHE_PREFIX = REDIS_NAMESPACE + "user-role:";
    // user权限缓存前缀
    String USER_PERMISSION_CACHE_PREFIX = REDIS_NAMESPACE + "cache-user-permission:";
    // user个性化配置前缀
    String USER_CONFIG_CACHE_PREFIX = REDIS_NAMESPACE + "cache-user-config:";
    // token缓存前缀
    String TOKEN_CACHE_PREFIX = REDIS_NAMESPACE + "cache-token:";

    // 存储在线用户的 zset前缀
    String ACTIVE_USERS_PREFIX = REDIS_NAMESPACE + "user-active:";

    // 排序规则： descend 降序
    String ORDER_DESC = "descend";
    // 排序规则： ascend 升序
    String ORDER_ASC = "ascend";

    // 按钮
    String TYPE_BUTTON = "1";
}
