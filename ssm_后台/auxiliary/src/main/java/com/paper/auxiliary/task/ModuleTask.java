package com.paper.auxiliary.task;

import com.paper.auxiliary.entity.user.User_Input;
import com.paper.auxiliary.entity.user.User_Log;
import com.paper.auxiliary.entity.user.User_Output;
import com.paper.auxiliary.service.Paper.AuxiliaryService;
import com.paper.auxiliary.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class ModuleTask {
    @Autowired
    private UserService userService;
    @Autowired
    private AuxiliaryService auxiliaryService;

//    @Scheduled(cron = "55 59 23 * * ?")
    @Scheduled(fixedDelay=1000)
    public void moduleProcessTask() throws IOException, InterruptedException {
        User_Log user_log = userService.getOneIsNotFinished();
        if(user_log == null)
        {
            Thread.sleep(5 * 1000);
            return;
        }
        List<User_Input> user_inputs = userService.getUserInputAllByLid(user_log.getLogId());
        switch (user_log.getModuleId())
        {
            case 1:{
                if(user_inputs.size()<1)
                {
                    userService.updateStatusByLogId(user_log.getLogId());
                    userService.addUserOutput(new User_Output(user_log.getLogId(),"输入数据错误！"));
                }else{
                    try {
                        String res = auxiliaryService.Theme(user_inputs.get(0).getInData());
                        userService.updateStatusByLogId(user_log.getLogId());
                        System.out.println(res);
                        userService.addUserOutput(new User_Output(user_log.getLogId(),res));
                    }catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }break;
            case 2:{
                if(user_inputs.size()<1)
                {
                    userService.updateStatusByLogId(user_log.getLogId());
                    userService.addUserOutput(new User_Output(user_log.getLogId(),"输入数据错误！"));
                }else{
                    try {
                        String res = auxiliaryService.Correction(user_inputs.get(0).getInData());
                        userService.updateStatusByLogId(user_log.getLogId());
                        userService.addUserOutput(new User_Output(user_log.getLogId(),res));
                    }catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }break;
            case 3:{
                if(user_inputs.size()<2)
                {
                    userService.updateStatusByLogId(user_log.getLogId());
                    userService.addUserOutput(new User_Output(user_log.getLogId(),"输入数据错误！"));
                }else{
                    try {
                        String res = auxiliaryService.Similarity(user_inputs.get(0).getInData(),user_inputs.get(1).getInData());
                        userService.updateStatusByLogId(user_log.getLogId());
                        userService.addUserOutput(new User_Output(user_log.getLogId(),res));
                    }catch(Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
            }break;
        }

//        System.out.println("User Analyse." + user_log);
    }
}
