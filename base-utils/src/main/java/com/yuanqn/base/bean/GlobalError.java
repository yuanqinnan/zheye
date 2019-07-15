package com.yuanqn.base.bean;

import lombok.Getter;

/**
 * @author:yuanqinnan
 * @date: 2019/7/13 10:30
 * @des: 全局错误码，0=成功，小于0系统异常，大于0业务错误
 */

@Getter
public enum GlobalError implements ErrorCode {

    SYSTEM_ERROR(-1, "服务器出错了，请稍后重试，带来不便请谅解，错误码-1"),
    DB_ERROR(-2, "服务器出错了，请稍后重试，带来不便请谅解，错误码-2"),
    REDIS_ERROR(-3, "服务器出错了，请稍后重试，带来不便请谅解，错误码-3"),
    SOA_ERROR(-4, "服务器出错了，请稍后重试，带来不便请谅解，错误码-4"),
    NETWORK_ERROR(-5, "服务器出错了，请稍后重试，带来不便请谅解，错误码-5"),
    SUCCESS(0, "成功"),
    NOT_LOGIN(1, "未登录或登录已失效"),
    WRONG_PARAM(2, "缺少参数或参数错误"),
    PERMISSION_DENIED(3, "没有权限"),
    COMMON_BIZ_ERROR(4, "一般业务异常"),
    EXCEED_LIMIT(5, "超过限额");
    ;

    private Integer code;
    private String message;

    GlobalError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}