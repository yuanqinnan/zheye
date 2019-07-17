package com.yuanqn.admin.system.controller;

import com.wf.captcha.Captcha;
import com.yuanqn.admin.common.controller.BaseController;
import com.yuanqn.admin.common.utils.CaptchaUtil;
import com.yuanqn.admin.common.utils.MD5Util;
import com.yuanqn.admin.system.model.form.LoginDTO;
import com.yuanqn.admin.system.model.vo.LoginVO;
import com.yuanqn.base.annotation.Limit;
import com.yuanqn.base.bean.WebJsonBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanqn
 * @date 2019/7/14 10:01
 */
@Validated
@RestController
@Api(tags = "登陆管理")
@RequestMapping("/account")
public class LoginController extends BaseController {

    @GetMapping("images/captcha")
    @ApiOperation(value = "获取登录验证码")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.outPng(110, 34, 4, Captcha.TYPE_ONLY_NUMBER, request, response);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录接口")
    @Limit(key = "login", count = 20, name = "登录接口", namespace = "ADMIN_ACCOUNT")
    public WebJsonBean<LoginVO> login(@RequestBody @Validated LoginDTO loginDTO) {
        String username = StringUtils.lowerCase(loginDTO.getUsername());
        String password = MD5Util.encrypt(username, loginDTO.getPassword());

        return WebJsonBean.ok();
    }


}
