package com.paper.auxiliary.task;

import com.paper.auxiliary.config.PerfAnalyseConfig;
import com.paper.auxiliary.config.SystemInfo;
import com.paper.auxiliary.entity.manager.*;
import com.paper.auxiliary.entity.user.Analyse_User;
import com.paper.auxiliary.service.Manage.ManageService;
import com.paper.auxiliary.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class AnalyseTask{
    @Autowired
    private ManageService manageService;
    @Autowired
    private UserService userService;
    @Autowired
    private PerfAnalyseConfig perfAnalyseConfig;

    public AnalyseTask() {

    }

    static private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private boolean test = true;

    @Scheduled(cron = "50 59 23 * * ?")
//    @Scheduled(cron = "0/10 * * * * ?")
    public void analyseUser(){
//        Long count = userService.getAllCountFromDate(simpleDateFormat.format(new Date()));
//        Map<?,?> map = userService.getUserLogCountFromDate(simpleDateFormat.format(new Date()));
        BigInteger count = userService.getAllCountByDate(simpleDateFormat.format(new Date()));
        List<Object[]> list = userService.getUserLogByDate(simpleDateFormat.format(new Date()));
//        System.out.println(count);
        for(Object[] iter : list)
        {
            BigInteger tmp = (BigInteger)iter[2];
            userService.addAnalyseUser(new Analyse_User((String)iter[0],(String)iter[1],tmp));
            count = count.subtract(tmp);
//            System.out.println((String)iter[0] + "   " + (String)iter[1] + "  " + (BigInteger)iter[2]);
        }
        if(count.compareTo(BigInteger.ZERO) == 1)
            userService.addAnalyseUser(new Analyse_User("null",null,count));
//        System.out.println(count);
//        System.out.println("User Analyse.");
    }

    @Scheduled(cron = "50 59 23 * * ?")
    //@Scheduled(cron = "0 0/3 13-20 * * ?")
    public void analyseFunction(){
        List<Module_Info> module_infos = manageService.getModuleInfoAll();
        for (Module_Info iter : module_infos)
        {
            BigInteger count = userService.getUserLogByModuleIdAndDate(iter.getModuleId(), simpleDateFormat.format(new Date()));
            //System.out.print("模块" + iter.getModuleId()+ ":  " + count);
            manageService.addAnalyseFunction(new Analyse_Function(iter.getModuleId(),count));
        }
        //System.out.println("Function Analyse Over.");
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void analysePerformance(){
        if(perfAnalyseConfig.getMapValue(1) == false && perfAnalyseConfig.getMapValue(2) == false && perfAnalyseConfig.getMapValue(3) == false)
            return;
        manageService.addAnalysePerformance(new Analyse_Performance(
                perfAnalyseConfig.getMapValue(1)? SystemInfo.cpuUsageMin():0 ,
                perfAnalyseConfig.getMapValue(2)? 1:0 ,
                perfAnalyseConfig.getMapValue(3)? SystemInfo.memoryUsageMin():0));
/*        manageService.addAnalysePerformance(new Analyse_Performance(
                (int)(Math.random()*99),
                (int)(Math.random()*99) ,
                (int)(Math.random()*99)));*/
//        System.out.println("Performance Analyse.");
    }


/*    private Integer autoCount = 0;
    @Scheduled(fixedDelay=3000)
    public void autoProductData()
    {
        userService.addUserLog(new User_Log("43gdj535j546",(int)(1+Math.random()*(3-1+1)),false));
//        System.out.println(SystemInfo.cpuUsageMin());
//        System.out.println(SystemInfo.memoryUsageMin());
        autoCount++;
        System.out.println(autoCount);
    }*/
}
