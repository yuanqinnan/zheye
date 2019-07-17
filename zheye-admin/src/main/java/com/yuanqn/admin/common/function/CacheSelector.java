package com.yuanqn.admin.common.function;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 19:14
 */
@FunctionalInterface
public interface CacheSelector<T> {
    T select() throws Exception;
}