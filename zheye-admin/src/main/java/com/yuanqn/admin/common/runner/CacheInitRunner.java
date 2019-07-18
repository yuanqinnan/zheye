package com.yuanqn.admin.common.runner;

import com.yuanqn.admin.system.entity.User;
import com.yuanqn.admin.system.manager.UserManager;
import com.yuanqn.admin.system.service.ICacheService;
import com.yuanqn.admin.system.service.IUserService;
import com.yuanqn.base.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author:yuanqinnan
 * @date: 2019/7/18 10:04
 */
@Component
@Slf4j
public class CacheInitRunner implements ApplicationRunner {


    @Autowired
    private IUserService userService;

    @Autowired
    private ICacheService cacheService;
    @Autowired
    private UserManager userManager;

    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(ApplicationArguments args) {
        try {
            log.info("Redis连接中 ······");
            boolean flag = cacheService.testConnect();
            if (!flag) {
                log.info("Redis连接异常，请检查Redis连接配置并确保Redis服务已启动");
                context.close();
                return;
            }

            log.info("缓存初始化 ······");
            log.info("缓存用户数据 ······");
            List<User> list = this.userService.list();
            for (User user : list) {
                userManager.loadUserRedisCache(user);
            }
        } catch (Exception e) {
            log.error("缓存初始化失败，{}", e.getMessage());
            log.error(" ____   __    _   _ ");
            log.error("| |_   / /\\  | | | |");
            log.error("|_|   /_/--\\ |_| |_|__");
            log.error("                        ");
            log.error("启动失败              ");
            if (e instanceof GlobalException)
                log.error("Redis连接异常，请检查Redis连接配置并确保Redis服务已启动");
            // 关闭
            context.close();
        }
    }
}