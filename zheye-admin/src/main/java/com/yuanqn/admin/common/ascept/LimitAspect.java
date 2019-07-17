package com.yuanqn.admin.common.ascept;

import com.google.common.collect.ImmutableList;
import com.pugwoo.wooutils.redis.RedisHelper;
import com.pugwoo.wooutils.redis.RedisLimitParam;
import com.pugwoo.wooutils.redis.RedisLimitPeroidEnum;
import com.yuanqn.admin.common.utils.IPUtil;
import com.yuanqn.base.annotation.Limit;
import com.yuanqn.base.enums.LimitType;
import com.yuanqn.base.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author:yuanqinnan
 * @date: 2019/7/17 15:23
 * @des:接口限流
 */
@Slf4j
@Aspect
@Component
public class LimitAspect {

    private final RedisHelper limitRedisTemplate;

    @Autowired
    public LimitAspect(RedisHelper redisHelper) {
        this.limitRedisTemplate = redisHelper;
    }

    @Pointcut("@annotation(com.yuanqn.base.annotation.Limit)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Limit limitAnnotation = method.getAnnotation(Limit.class);
        LimitType limitType = limitAnnotation.limitType();
        String name = limitAnnotation.name();
        String key;
        String ip = IPUtil.getIpAddr(request);
        RedisLimitPeroidEnum redisLimitPeroidEnum = limitAnnotation.periodType();
        int limitCount = limitAnnotation.count();
        RedisLimitParam redisLimitParam = new RedisLimitParam();
        redisLimitParam.setLimitCount(limitCount);
        redisLimitParam.setLimitPeroid(redisLimitPeroidEnum);

        switch (limitType) {
            case IP:
                key = ip;
                break;
            case CUSTOMER:
                key = limitAnnotation.key();
                break;
            default:
                key = StringUtils.upperCase(method.getName());
        }

        redisLimitParam.setNamespace(limitAnnotation.namespace());
        long count = limitRedisTemplate.useLimitCount(redisLimitParam, key);
        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnnotation.namespace() + "_", key, ip));
        log.info("IP:{} 第 {} 次访问key为 {}，描述为 [{}] 的接口", ip, count, keys, name);
        if (count <= limitCount) {
            return point.proceed();
        } else {
            throw new GlobalException("接口访问超出频率限制");
        }
    }

}