package com.yuanqn.base.bean;

import lombok.Getter;

/**
 * @author:yuanqinnan
 * @date: 2019/7/13 10:30
 * @des: 全局错误码，0=成功，小于0系统异常，大于0业务错误
 */

@Getter
public enum GlobalError implements ErrorCode {
    ;

    private Integer code;
    private String message;

    GlobalError(int code, String message) {
        this.code = code;
        this.message = message;
    }
}