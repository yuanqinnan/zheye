package com.yuanqn.admin.common.ascept;

import com.pugwoo.wooutils.json.JSON;
import com.yuanqn.admin.common.utils.HttpContextUtils;
import com.yuanqn.admin.system.entity.Log;
import com.yuanqn.admin.system.service.ILogService;
import com.yuanqn.base.annotation.SysLog;
import com.yuanqn.admin.common.utils.IPUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author:yuanqinnan
 * @date: 2019/7/16 13:39
 */

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private ILogService logService;

    @Pointcut("@annotation(com.yuanqn.base.annotation.SysLog)")
    public void pointcut() {
        // do nothing
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = joinPoint.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Log sysLog = new Log();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = new JSON().toJson(args[0]);
            sysLog.setParams(params);
        } catch (Exception e) {

        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setIp(IPUtil.getIpAddr(request));

        //用户名
        String username = ((User) SecurityUtils.getSubject().getPrincipal()).getUsername();
        sysLog.setUsername(username);
        sysLog.setTime(time);
        sysLog.setCreateTime(new Date());
        //保存系统日志
        logService.save(sysLog);
        return result;
    }
}