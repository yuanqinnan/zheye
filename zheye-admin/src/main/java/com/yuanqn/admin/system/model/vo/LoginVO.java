package com.yuanqn.admin.system.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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


}