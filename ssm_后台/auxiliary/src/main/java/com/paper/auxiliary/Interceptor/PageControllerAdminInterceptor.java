package com.paper.auxiliary.Interceptor;

import com.paper.auxiliary.entity.manager.Admin_Info;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
public class PageControllerAdminInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(PageControllerAdminInterceptor.class);

    @Pointcut("(execution(* com.paper.auxiliary.controller.ManagePageController.*(..)) " +
                "&& (@annotation(org.springframework.web.bind.annotation.GetMapping) " +
                "|| @annotation(org.springframework.web.bind.annotation.PostMapping))) " +
            "|| execution(* com.paper.auxiliary.controller.IndexPageController.*(..)) " +
            "|| (execution(* com.paper.auxiliary.controller.UserPageController.*(..)) " +
                "&& (@annotation(org.springframework.web.bind.annotation.GetMapping) " +
                "|| @annotation(org.springframework.web.bind.annotation.PostMapping))) ")
    /*@Pointcut("execution(* com.paper.auxiliary.controller.ManagePageController.index(..)) ")*/
    public void adminLoginInterceptorPointcut() { }

    @Pointcut("execution(* com.paper.auxiliary.controller.ManagePageController.info*(..)) " +
            "|| execution(* com.paper.auxiliary.controller.UserPageController.info*(..))")
    /*@Pointcut("execution(* com.paper.auxiliary.controller.ManagePageController.index(..)) ")*/
    public void adminUserPermisInterceptorPointcut() { }

    @Around("adminLoginInterceptorPointcut()")
    public Object adminLogCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = "redirect:/management/login";
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Admin_Info admin_info = (Admin_Info) request.getSession().getAttribute("Admin_Info");
        if(admin_info == null) return obj;

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        Cookie [] cookies = request.getCookies();
//        System.out.println("123");
        for(Cookie cks : cookies)
        {
            if(cks.getName().equals("Login_Account"))
            {
                Cookie cookie = new Cookie("Login_Account","");
                cookie.setMaxAge(600);              // cookie更新
                cookie.setPath("/");                // 设置路径
                response.addCookie(cookie);         // 修改cookie

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
