package com.paper.auxiliary.Interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Pointcut("(execution(* com.paper.auxiliary.controller.*.*(..)))" +
            "&& (@annotation(org.springframework.web.bind.annotation.GetMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)) " +
            "|| @annotation(org.springframework.web.bind.annotation.RequestMapping)) ")
    public void logInterceptorPointcut() { }

    @Around("logInterceptorPointcut()")
    public Object logRecord(ProceedingJoinPoint joinPoint){
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error(throwable.getMessage());
        }
        if(obj!= null)
        {
            logger.info("★ EXECUTE： " + joinPoint.getSignature().toShortString());
        }else{
            logger.error("★★ RETURNING ERROR： " + joinPoint.getSignature().toString()/* + " ( " + joinPoint.getArgs().toString() + ")"*/);
        }
        return obj;
    }
}
