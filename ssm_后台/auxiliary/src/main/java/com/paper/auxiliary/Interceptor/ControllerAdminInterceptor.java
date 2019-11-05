package com.paper.auxiliary.Interceptor;

import com.paper.auxiliary.config.SystemInfo;
import com.paper.auxiliary.entity.manager.Admin_Info;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Aspect
@Component
public class ControllerAdminInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAdminInterceptor.class);

/*    @Pointcut("(execution(* com.paper.auxiliary.controller.ManageController.*(..))" +
            "   && @annotation(org.springframework.web.bind.annotation.RequestMapping))" +
            "|| (execution(* com.paper.auxiliary.controller.UserController.*(..))" +
            "   && @annotation(org.springframework.web.bind.annotation.RequestMapping))")*/
    @Pointcut("(" +
            "execution(* com.paper.auxiliary.controller.ManageController.*(..)) " +
            "|| execution(* com.paper.auxiliary.controller.UserController.*(..))" +
            ")" +
            "  && @annotation(org.springframework.web.bind.annotation.RequestMapping))")
    public void testUserLoginInterceptorPointcut()
    { }

    @Around("testUserLoginInterceptorPointcut()")
    public Object userLoginCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Object obj = null;

//        System.out.println("hello user " + admin_info);

        try {
            obj = joinPoint.proceed();
            return obj;
        } catch (Exception e) {
            System.out.println(e);
        }


//        if(admin_info != null) {
//            System.out.println("Session is not null");
//        }

        Admin_Info admin_info = (Admin_Info) request.getSession().getAttribute("Admin_Info");
        if(admin_info == null) {
            System.out.println("Session is null");
            return obj;
        }
        System.out.println("Session is not null");


        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie[] cookies = request.getCookies();
        for(Cookie cks : cookies)
        {
            if(cks.getName().equals("Login_Account"))
            {
                Cookie cookie = new Cookie("Login_Account","");
                cookie.setMaxAge(600);              // cookie更新
                cookie.setPath("/");                // 设置路径
                response.addCookie(cookie);         // 修改cookie
                System.out.println("Cookie is not null");

                try {
                    obj = joinPoint.proceed();
                } catch (Exception e) {
                    System.out.println("Admin Login Exceptioin: "+e);
                }
                break;
            }
        }
        return obj;
    }
}
