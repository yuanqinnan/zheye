package com.yuanqn.admin.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.pugwoo.wooutils.redis.RedisHelper;
import com.wf.captcha.Captcha;
import com.yuanqn.admin.common.authentication.JWTToken;
import com.yuanqn.admin.common.authentication.JWTUtil;
import com.yuanqn.admin.common.config.Constant;
import com.yuanqn.admin.common.controller.BaseController;
import com.yuanqn.admin.common.enums.UserStatusEnum;
import com.yuanqn.admin.common.model.ActiveUser;
import com.yuanqn.admin.common.properties.ZheyeProperties;
import com.yuanqn.admin.common.utils.*;
import com.yuanqn.admin.system.entity.User;
import com.yuanqn.admin.system.manager.UserManager;
import com.yuanqn.admin.system.pojo.dto.LoginDTO;
import com.yuanqn.admin.system.pojo.vo.LoginVO;
import com.yuanqn.admin.system.service.IUserService;
import com.yuanqn.base.annotation.Limit;
import com.yuanqn.base.bean.WebJsonBean;
import com.yuanqn.base.exception.GlobalException;
import com.yuanqn.base.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * @author yuanqn
 * @date 2019/7/14 10:01
 */
@Validated
@RestController
@Api(tags = "登陆管理")
@RequestMapping("/account")
public class LoginController extends BaseController {

    @Autowired
    private UserManager userManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private ZheyeProperties properties;

    @Autowired
    private RedisHelper redisHelper;

    @GetMapping("images/captcha")
    @ApiOperation(value = "获取登录验证码")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CaptchaUtil.outPng(110, 34, 4, Captcha.TYPE_ONLY_NUMBER, request, response);
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录接口")
    @Limit(key = "login", count = 20, name = "登录接口", namespace = "ADMIN_ACCOUNT")
    public WebJsonBean<LoginVO> login(@RequestBody @Validated LoginDTO loginDTO, HttpServletRequest request) throws Exception {
        String username = StringUtils.lowerCase(loginDTO.getUsername());
        String password = MD5Util.encrypt(username, loginDTO.getPassword());
        final String errorMessage = "用户名或密码错误";
        User user = this.userManager.getUser(username);
        if (user == null)
            throw new GlobalException(errorMessage);
        if (!StringUtils.equals(user.getPassword(), password))
            throw new GlobalException(errorMessage);
        if (StringUtils.equals(user.getStatus(), UserStatusEnum.LOCK.getCode())) {
            throw new GlobalException(errorMessage);
        }
        // 更新用户登录时间
        this.userService.updateLoginTime(username);
        String token = CommonUtil.encryptToken(JWTUtil.sign(username, password));
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getShiro().getJwtTimeOut());
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);
        String userId = this.saveTokenToRedis(user, jwtToken, request);
        user.setId(userId);
        LoginVO loginVO = new LoginVO();
        loginVO.setToken(jwtToken.getToken());
        loginVO.setRoles(this.userManager.getUserRoles(username));
        loginVO.setPermissions(this.userManager.getUserPermissions(username));
        loginVO.setUserConfig(this.userManager.getUserConfig(String.valueOf(user.getUserId())));
        loginVO.setUser(user);
        return WebJsonBean.ok(loginVO);
    }


    private String saveTokenToRedis(User user, JWTToken token, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIpAddr(request);
        // 构建在线用户
        ActiveUser activeUser = new ActiveUser();
        activeUser.setUsername(user.getUsername());
        activeUser.setIp(ip);
        activeUser.setToken(token.getToken());
        activeUser.setLoginAddress(AddressUtil.getCityInfo(ip));

        // redis 存储登录用户，score 为过期时间戳
        this.redisHelper.setObject(Constant.ACTIVE_USERS_PREFIX, Integer.valueOf(token.getExpireAt()), activeUser);
        // redis 中存储这个加密 token，key = 前缀 + 加密 token + .ip
        this.redisHelper.setObject(Constant.TOKEN_CACHE_PREFIX + token.getToken() + StringPool.COLON + ip, properties.getShiro().getJwtTimeOut(), token.getToken());

        return activeUser.getId();
    }

}
