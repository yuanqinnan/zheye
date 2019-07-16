package com.yuanqn.admin.system.controller;

import com.wf.captcha.Captcha;
import com.yuanqn.admin.common.controller.BaseController;
import com.yuanqn.admin.common.utils.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
