package com.yuanqn.admin.config;

import com.pugwoo.wooutils.cache.HiSpeedCacheAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HiSpeedCacheAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    HiSpeedCacheAspect hiSpeedCacheAspect() {
        return new HiSpeedCacheAspect();
    }

}
