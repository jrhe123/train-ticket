package com.he.train.common.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.PropertyPreFilters;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Aspect
@Component
public class LogAspect {
    public LogAspect() {
        System.out.println("$$$ Common LogAspect $$$");
    }

    private final static Logger LOG = LoggerFactory.getLogger(LogAspect.class);

    /**
     * $$$ Add a point cut $$$
     */
    @Pointcut("execution(public * com.he..*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Before("controllerPointcut()")
    public void doBefore(JoinPoint joinPoint) {
//        // Add custom attribute "LOG_ID"
//        MDC.put("LOG_ID", System.currentTimeMillis() + RandomUtil.randomString(3));

        // Logger
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Signature signature = joinPoint.getSignature();
        String name = signature.getName();

        // Request information
        LOG.info("------------- BEGIN -------------");
        LOG.info("Request URL: {} {}", request.getRequestURL().toString(), request.getMethod());
        LOG.info("Class Name: {}.{}", signature.getDeclaringTypeName(), name);
        LOG.info("Remote Addr: {}", request.getRemoteAddr());

        // request args
        Object[] args = joinPoint.getArgs();
        // LOG.info("请求参数: {}", JSONObject.toJSONString(args));

        // Ignore special args, e.g., file type
        Object[] arguments = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                continue;
            }
            arguments[i] = args[i];
        }
        /**
         * Reserved fields, sensitive fields or fields that are too long are not displayed:
         * ID, mobile number, email address, password, etc.
         */
        String[] excludeProperties = {"mobile", "password",};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOG.info("Request params: {}", JSONObject.toJSONString(arguments, excludefilter));
    }

    @Around("controllerPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        /**
         * Reserved fields, sensitive fields or fields that are too long are not displayed:
         * ID, mobile number, email address, password, etc.
         */
        String[] excludeProperties = {"mobile", "password",};
        PropertyPreFilters filters = new PropertyPreFilters();
        PropertyPreFilters.MySimplePropertyPreFilter excludefilter = filters.addFilter();
        excludefilter.addExcludes(excludeProperties);
        LOG.info("Return response: {}", JSONObject.toJSONString(result, excludefilter));
        LOG.info("------------- END Time Elapse {} ms -------------", System.currentTimeMillis() - startTime);
        return result;
    }

}
