package com.yuanqn.admin.system.pojo.vo;

import com.yuanqn.admin.system.entity.User;
import com.yuanqn.admin.system.entity.UserConfig;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 14:20
 * @des: 登录成功后返回前端数据
 */

@Data
@ApiModel("登录成功后数据")
public class LoginVO {

    @ApiModelProperty(value = "登录token")
    private String token;

    @ApiModelProperty(value = "过期时间")
    private String expireTime;

    @ApiModelProperty(value = "用户角色")
    private Set<String> roles;

    @ApiModelProperty(value = "用户权限")
    private Set<String> permissions;

    @ApiModelProperty(value = "用户配置")
    private UserConfig userConfig;

    @ApiModelProperty(value = "用户信息")
    private User user;

}