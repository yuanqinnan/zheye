package com.yuanqn.base.bean;

import java.io.Serializable;

/**
 * @author:yuanqinnan
 * @date: 2019/7/13 10:26
 */

public interface ErrorCode extends Serializable {

    /**
     * 错误码，0=成功，<0系统错误，>0业务错误
     *
     * @return
     */
    Integer getCode();

    /**
     * 错误信息
     *
     * @return
     */
    String getMessage();
}