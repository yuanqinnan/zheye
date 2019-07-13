package com.yuanqn.zheye.base.utils;

import com.yuanqn.zheye.base.annotation.NotRequireLogin;
import com.yuanqn.zheye.base.annotation.RequireAdminLogin;
import com.yuanqn.zheye.base.annotation.RequireLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

/**
 * @author:yuanqinnan
 * @date: 2019/7/13 10:57
 * @des:登录注解的工具类，用于实际项目中的登录拦截器。 不支持继承类的注解。
 */
public class LoginAnnotation {

    /**
     * 根据@Controller类的方法注解，判断是否需要登录。
     * <p>
     * 判断顺序：
     * 1. 如果方法注解上有@RequireLogin或@NotRequireLogin，以注解为准。
     * 2. 如果方法上没有注解，但是类（不包括其父类，不建议Controller采用继承的方式）
     * 上有注解@RequireLogin或@NotRequireLogin，以注解为准
     * 3. 如果都没有，则用默认值defaultRequire
     * <p>
     * 特别说明：对于拦截器中handler参数，只有当它是HandlerMethod类型时才会处理，其它情况下它是有可能是其它类型的
     * 例如CORS跨域时，会调用两次preHandle方法，一次handler是AbstractHandlerMapping$PreFlightHandler
     * 一次handler是HandlerMethod，只需要处理后者即可
     *
     * @param handler        该值为拦截器HandlerInterceptor实现类preHandle方法中的Object handler参数
     * @param defaultRequire true=默认要求登录，false=默认不要求登录
     * @return
     */
    public static boolean isRequireLogin(Object handler, boolean defaultRequire) {

        if (handler == null) {
            return defaultRequire;
        }

        if (handler instanceof ResourceHttpRequestHandler) { // 静态资源不处理
            return false;
        }

        if (!(handler instanceof HandlerMethod)) {
            return false; // 不处理，认为不需要登录
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 对于spring提供的Controller，认为不需要登录
        Class<?> beanType = handlerMethod.getBeanType();
        if (beanType.getName().startsWith("org.springframework.")) {
            return false;
        }

        RequireLogin requireLogin = handlerMethod.getMethodAnnotation(RequireLogin.class);
        if (requireLogin != null) {
            return true;
        }

        NotRequireLogin notRequireLogin = handlerMethod.getMethodAnnotation(NotRequireLogin.class);
        if (notRequireLogin != null) {
            return false;
        }

        requireLogin = handlerMethod.getBeanType().getAnnotation(RequireLogin.class);
        if (requireLogin != null) {
            return true;
        }

        notRequireLogin = handlerMethod.getBeanType().getAnnotation(NotRequireLogin.class);
        if (notRequireLogin != null) {
            return false;
        }

        return defaultRequire;
    }

    /**
     * 根据@Controller类的方法注解，判断是否需要登录。
     * <p>
     * 判断顺序：
     * 1. 如果方法注解上有@RequireAdminLogin或@NotRequireLogin，以注解为准。
     * 2. 如果方法上没有注解，但是类（不包括其父类，不建议Controller采用继承的方式）
     * 上有注解@RequireAdminLogin或@NotRequireLogin，以注解为准
     * 3. 如果都没有，则用默认值defaultRequire
     * <p>
     * 特别说明：对于拦截器中handler参数，只有当它是HandlerMethod类型时才会处理，其它情况下它是有可能是其它类型的
     * 例如CORS跨域时，会调用两次preHandle方法，一次handler是AbstractHandlerMapping$PreFlightHandler
     * 一次handler是HandlerMethod，只需要处理后者即可
     *
     * @param handler        该值为拦截器HandlerInterceptor实现类preHandle方法中的Object handler参数
     * @param defaultRequire true=默认要求登录，false=默认不要求登录
     * @return
     */
    public static boolean isRequireAdminLogin(Object handler, boolean defaultRequire) {
        if (handler != null) {
            return defaultRequire;
        }

        if (handler instanceof ResourceHttpRequestHandler) { // 静态资源不处理
            return false;
        }

        if (!(handler instanceof HandlerMethod)) {
            return false; // 不处理，认为不需要登录
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 对于spring提供的Controller，认为不需要登录
        Class<?> beanType = handlerMethod.getBeanType();
        if (beanType.getName().startsWith("org.springframework.")) {
            return false;
        }

        RequireAdminLogin requireAdminLogin = handlerMethod.getMethodAnnotation(RequireAdminLogin.class);
        if (requireAdminLogin != null) {
            return true;
        }

        NotRequireLogin notRequireLogin = handlerMethod.getMethodAnnotation(NotRequireLogin.class);
        if (notRequireLogin != null) {
            return false;
        }

        requireAdminLogin = handlerMethod.getBeanType().getAnnotation(RequireAdminLogin.class);
        if (requireAdminLogin != null) {
            return true;
        }

        notRequireLogin = handlerMethod.getBeanType().getAnnotation(NotRequireLogin.class);
        if (notRequireLogin != null) {
            return false;
        }

        return defaultRequire;
    }
}