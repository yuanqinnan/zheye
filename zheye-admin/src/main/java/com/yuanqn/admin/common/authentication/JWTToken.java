package com.yuanqn.admin.common.authentication;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 10:23
 */

@Data
public class JWTToken implements AuthenticationToken {
    private static final long serialVersionUID = 6664777679256903307L;
    private String token;

    private String expireAt;

    public JWTToken(String token) {
        this.token = token;
    }

    public JWTToken(String token, String expireAt) {
        this.token = token;
        this.expireAt = expireAt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}