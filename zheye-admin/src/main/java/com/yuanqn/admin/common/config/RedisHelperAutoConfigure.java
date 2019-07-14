package com.yuanqn.admin.common.config;

import com.pugwoo.wooutils.redis.RedisHelper;
import com.pugwoo.wooutils.redis.RedisSyncAspect;
import com.pugwoo.wooutils.redis.impl.JsonRedisObjectConverter;
import com.pugwoo.wooutils.redis.impl.RedisHelperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
@ConditionalOnProperty(prefix = "spring.redis", value = "host")
public class RedisHelperAutoConfigure {

    @Autowired
    private RedisProperties redisProperties;

    @Bean("redisHelper")
    RedisHelper redisHelper() {
        RedisHelperImpl helper = new RedisHelperImpl();
        helper.setHost(redisProperties.getHost());
        helper.setPort(redisProperties.getPort());
        helper.setPassword(redisProperties.getPassword());
        helper.setDatabase(redisProperties.getDatabase());
        helper.setRedisObjectConverter(new JsonRedisObjectConverter());
        return helper;
    }

    @Bean
    @ConditionalOnMissingBean
    RedisSyncAspect redisSyncAspect() {
        return new RedisSyncAspect();
    }

}
