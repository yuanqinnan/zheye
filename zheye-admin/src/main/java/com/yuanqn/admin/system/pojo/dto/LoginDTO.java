package com.yuanqn.admin.system.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 14:33
 * @des: 登录参数
 */
@Data
public class LoginDTO {


    @NotEmpty(message = "用户名为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotEmpty(message = "密码为空")
    @ApiModelProperty(value = "密码", required = true)
    private String password;
}