package com.yuanqn.base.annotation;

import com.pugwoo.wooutils.redis.RedisLimitPeroidEnum;
import com.yuanqn.base.enums.LimitType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 15:22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Limit {

    // 资源名称，用于描述接口功能
    String name() default "";

    // 资源 key
    String key() default "";

    // key 命令空间
    String namespace() default "";

    // 限制访问次数
    int count();

    // 限制类型
    LimitType limitType() default LimitType.CUSTOMER;

    // 时间的，单位秒
    RedisLimitPeroidEnum periodType() default RedisLimitPeroidEnum.SECOND;
}