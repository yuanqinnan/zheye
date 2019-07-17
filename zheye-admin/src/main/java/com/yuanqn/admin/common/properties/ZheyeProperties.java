package com.yuanqn.admin.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 16:49
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "zheye")
public class ZheyeProperties {

    private ShiroProperties shiro = new ShiroProperties();

    private boolean openAopLog = true;
}