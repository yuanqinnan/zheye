package com.yuanqn.zheye.base.exception;

import java.io.Serializable;

/**
 * @author:yuanqinnan
 * @date: 2019/7/13 10:43
 * @des: 错误级别自定义枚举，用于异常处理器使用决定打印什么级别的日志
 */
public enum LogLevelEnum implements Serializable {

    /**
     * WARN 警告级别，不打出Exception堆栈
     */
    WARN,
    /**
     * INFO 消息级别，不打出Exception堆栈
     */
    INFO,
    /**
     * ERROR 消息级别，不打出Exception堆栈，系统默认
     */
    ERROR,
    /**
     * ERROR 消息级别，打出Exception堆栈
     */
    ERROR_DETAIL;
}
