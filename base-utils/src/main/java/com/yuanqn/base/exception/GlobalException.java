package com.yuanqn.base.exception;

import com.yuanqn.base.bean.ErrorCode;
import com.yuanqn.base.bean.GlobalError;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author:yuanqinnan
 * @date: 2019/7/13 10:44
 * @des:
 */
public class GlobalException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 7470299486191744640L;

    private LogLevelEnum logLevel = LogLevelEnum.ERROR;

    private DefaultErrorCode errorCode;

    @Getter
    @Setter
    public static class DefaultErrorCode implements ErrorCode, Serializable {

        private static final long serialVersionUID = 3490842223314408467L;

        private Integer code;
        private String message;

    }

    public GlobalException() {
    }

    /**
     * @param globalError 错误码
     */
    public GlobalException(ErrorCode globalError) {
        super(globalError.getMessage());
        this.errorCode = new DefaultErrorCode();
        this.errorCode.setCode(globalError.getCode());
        this.errorCode.setMessage(globalError.getMessage());
    }

    /**
     * @param globalError 错误码
     * @param logLevel    日志级别
     */
    public GlobalException(ErrorCode globalError, LogLevelEnum logLevel) {
        super(globalError.getMessage());
        this.errorCode = new DefaultErrorCode();
        this.errorCode.setCode(globalError.getCode());
        this.errorCode.setMessage(globalError.getMessage());
        this.logLevel = logLevel;
    }

    public GlobalException(GlobalError globalError, String messages) {
        super(globalError.getMessage());
        this.errorCode = new DefaultErrorCode();
        this.errorCode.setCode(globalError.getCode());
        this.errorCode.setMessage(messages);
    }

    public GlobalException(String messages) {
        super(messages);
        this.errorCode = new DefaultErrorCode();
        this.errorCode.setCode(GlobalError.SYSTEM_ERROR.getCode());
        this.errorCode.setMessage(messages);
    }

    public ErrorCode getGlobalError() {
        return errorCode;
    }

    public LogLevelEnum getLogLevel() {
        return logLevel;
    }
}