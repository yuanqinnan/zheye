package com.yuanqn.admin.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yuanqn.base.utils.DateUtil;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author:yuanqinnan
 * @date: 2019/7/18 13:17
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActiveUser implements Serializable {
    private static final long serialVersionUID = 2350959324310561684L;
    // 唯一编号
    private String id = RandomStringUtils.randomAlphanumeric(20);
    // 用户名
    private String username;
    // ip地址
    private String ip;
    // token(加密后)
    private String token;
    // 登录时间
    private String loginTime = DateUtil.formatFullTime(LocalDateTime.now(),DateUtil.FULL_TIME_SPLIT_PATTERN);
    // 登录地点
    private String loginAddress;
}