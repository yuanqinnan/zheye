package com.yuanqn.admin.common.handler;

import com.yuanqn.base.bean.GlobalError;
import com.yuanqn.base.bean.WebJsonBean;
import com.yuanqn.base.exception.GlobalException;
import com.yuanqn.base.exception.LogLevelEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author:yuanqinnan
 * @date: 2019/7/15 11:15
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public WebJsonBean handleValidate(ConstraintViolationException ex) {
        return WebJsonBean.fail(GlobalError.WRONG_PARAM, ex.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public WebJsonBean bindExceptionHandler(BindException ex) {
        String message = getMessage(ex.getAllErrors());
        if(message == null) {
            message = ex.getMessage();
        }
        return WebJsonBean.fail(GlobalError.WRONG_PARAM, message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebJsonBean handleBindException(MethodArgumentNotValidException ex) {
        String message = getMessage(ex.getBindingResult().getAllErrors());
        if(message == null) {
            message = ex.getMessage();
        }
        return WebJsonBean.fail(GlobalError.WRONG_PARAM, message);
    }

    /**
     * 全局异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler
    public WebJsonBean exceptionHandler(Exception ex) {

        //预处理dubbo抛出异常
        if (!(ex instanceof GlobalException)) {
            Throwable t = ex;
            while ((t = t.getCause()) != null) {
                if (t instanceof GlobalException) {
                    ex = (GlobalException) t;
                    break;
                }
            }
        }

        // 打印异常信息
        if (ex instanceof GlobalException) {
            LogLevelEnum logLevel = ((GlobalException) ex).getLogLevel();
            if (logLevel == LogLevelEnum.WARN) {
                log.warn(ex.getMessage());
            } else if (logLevel == logLevel.INFO) {
                log.info(ex.getMessage());
            } else if (logLevel == logLevel.ERROR) {
                log.error(ex.getMessage());
            } else {
                log.error(ex.getMessage(), ex);
            }
        } else {
            log.error(ex.getMessage(), ex);
        }

        String exClassName = ex.getClass().getName();

        if (ex instanceof GlobalException) {
            return WebJsonBean.fail(((GlobalException) ex).getGlobalError());
        } else if (exClassName.startsWith("org.springframework.dao")
                || exClassName.startsWith("org.springframework.jdbc")) {
            return WebJsonBean.fail(GlobalError.DB_ERROR);
        } else {
            return WebJsonBean.fail(null);
        }
    }


    private String getMessage(List<ObjectError> errors) {
        if(errors == null || errors.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : errors) {
            if(sb.length() > 0) {sb.append("; ");}
            if(error instanceof FieldError) {
                FieldError fe = (FieldError) error;
                sb.append(fe.getField()).append(fe.getDefaultMessage());
            } else {
                sb.append(error.getDefaultMessage());
            }
        }
        return sb.toString();
    }
}