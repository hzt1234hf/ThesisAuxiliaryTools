package com.paper.auxiliary.controller;

import com.paper.auxiliary.entity.user.User_Info;
import com.paper.auxiliary.entity.user.User_Input;
import com.paper.auxiliary.entity.user.User_Log;
import com.paper.auxiliary.service.User.UserService;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/aux")
public class PaperController {
    @Autowired
    private UserService userService;

    private Logger logback = LoggerFactory.getLogger(PaperController.class);

    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private Boolean isExistUserInfo(String openid)
    {
        User_Info user_info = userService.getUserInfoByOid(openid);
        return user_info == null;
    }

    @RequestMapping(value = "/similarity",method = RequestMethod.POST)
    public String getSimilarity(String openid, String first, String second, HttpServletResponse response,
                                 HttpServletRequest request) throws IOException, InterruptedException {
        try{
            if(isExistUserInfo(openid))
                return "非法操作！";
            logback.info("★★★ Request Client IP Address: " + request.getRemoteAddr() + "  , Request Method 'getCorrection(String, String)', Current Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            userService.updateUserInfoDate(openid);
            Integer lastCount = userService.getIsNotFinishedCount();
            User_Log user_log = userService.addUserLog(new User_Log(openid,3,false));
            userService.addUserInput(new User_Input(user_log.getLogId(),first));
            userService.addUserInput(new User_Input(user_log.getLogId(),second));
            if(lastCount<1)
                return "请前往历史记录查询处理结果...";
            else
                return "任务已提交，前面还有 " + lastCount + " 个任务正在处理，请前往历史记录查询处理结果...";
        }catch(Exception e)
        {
            logback.info(e.getMessage());
        }
        return "系统异常！";
    }

    @RequestMapping(value = "/correction",method = RequestMethod.POST)
    public String getCorrection(String openid, String text, HttpServletResponse response,
                                 HttpServletRequest request) throws IOException, InterruptedException {
        try{
            if(isExistUserInfo(openid))
                return "非法操作！";
            logback.info("★★★ Request Client IP Address: " + request.getRemoteAddr() + "  , Request Method 'getCorrection(String, String)', Current Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            userService.updateUserInfoDate(openid);
            Integer lastCount = userService.getIsNotFinishedCount();
            User_Log user_log = userService.addUserLog(new User_Log(openid,2,false));
            userService.addUserInput(new User_Input(user_log.getLogId(),text));
//            return auxiliaryService.Correction(text);
//            res = auxiliaryService.Correction(text);

            if(lastCount<1)
                return "请前往历史记录查询处理结果...";
            else
                return "任务已提交，前面还有 " + lastCount + " 个任务正在处理，请前往历史记录查询处理结果...";
        }catch(Exception e)
        {
            logback.info(e.getMessage());
        }
        return "系统异常！";
    }

    @RequestMapping(value = "/theme",method = RequestMethod.POST)
    public String getTheme(String openid, String text, HttpServletResponse response,
                            HttpServletRequest request) throws IOException, InterruptedException {
        try{
            if(isExistUserInfo(openid))
                return "非法操作！";
            logback.info("★★★ Request Client IP Address: " + request.getRemoteAddr() + "  , Request Method 'getCorrection(String, String)', Current Time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            userService.updateUserInfoDate(openid);
            Integer lastCount = userService.getIsNotFinishedCount();
            User_Log user_log = userService.addUserLog(new User_Log(openid,1,false));
            userService.addUserInput(new User_Input(user_log.getLogId(),text));
            if(lastCount<1)
                return "请前往历史记录查询处理结果...";
            else
                return "任务已提交，前面还有 " + lastCount + " 个任务正在处理，请前往历史记录查询处理结果...";
        }catch(Exception e)
        {
            logback.info(e.getMessage());
        }
        return "系统异常！";
    }
}
