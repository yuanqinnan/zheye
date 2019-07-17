package com.yuanqn.admin.common.function;

import com.yuanqn.base.exception.GlobalException;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 19:15
 */
@FunctionalInterface
public interface JedisExecutor<T, R> {
    R excute(T t) throws GlobalException;
}