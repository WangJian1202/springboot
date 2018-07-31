package com.springboot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public  * com.springboot.demo.*.*(..))")
    public void log() {

    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("Before...");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
//        url
        logger.info("url:{}", request.getRequestURL());
//        method
        String method = request.getMethod();
        logger.info("method:{}", method);
//        ip
        String remoteAddr = request.getRemoteAddr();
        logger.info("ip:{}", remoteAddr);
//        类方法
        logger.info("class_method:{}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        参数
        logger.info("args:{}", joinPoint.getArgs());
    }

    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterRuterning(Object object) {
        logger.info("response:{}", object.toString());

    }

    @After("log()")
    public void doAfter() {
        logger.info("After...");
    }
}
