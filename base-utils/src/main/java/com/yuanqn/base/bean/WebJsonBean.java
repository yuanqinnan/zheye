package com.yuanqn.base.bean;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author:yuanqinnan
 * @date: 2019/7/13 10:33
 * @des:
 */

@Getter
@Setter
public class WebJsonBean<T> implements Serializable {
    private static final long serialVersionUID = -6623446951514743699L;
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 时间线
     */
    private Long timestamp;

    /**
     * 返回数据
     */
    private T data;

    public static WebJsonBean ok() {
        return ok(null);
    }

    public static <T> WebJsonBean<T> ok(T data) {
        WebJsonBean<T> res = new WebJsonBean();
        res.setCode(0);
        res.setMessage("成功");
        res.setTimestamp(System.currentTimeMillis());
        res.setData(data);
        return res;
    }

    public static WebJsonBean fail(ErrorCode errorCode) {
        WebJsonBean res = new WebJsonBean();
        if (errorCode != null) {
            res.setCode(errorCode.getCode());
            res.setMessage(errorCode.getMessage());
        } else {
            res.setCode(-1);
            res.setMessage("系统未知错误");
        }
        res.setTimestamp(System.currentTimeMillis());
        return res;
    }

    public static WebJsonBean fail(ErrorCode errorCode, String message) {
        WebJsonBean res = new WebJsonBean();
        if (errorCode != null) {
            res.setCode(errorCode.getCode());
            res.setMessage(message);
        } else {
            res.setCode(-1);
            res.setMessage("系统未知错误");
        }
        res.setTimestamp(System.currentTimeMillis());
        return res;
    }

}