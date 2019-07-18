package com.yuanqn.admin.common.properties;

import lombok.Data;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 16:51
 */
@Data
public class ShiroProperties {

    private String anonUrl;
    /**
     * token默认有效时间 1天
     */
    private Integer jwtTimeOut = 3600 * 24;
}