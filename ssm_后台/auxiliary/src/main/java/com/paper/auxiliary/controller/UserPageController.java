package com.paper.auxiliary.controller;

import com.paper.auxiliary.config.SystemOption;
import com.paper.auxiliary.entity.user.User_Feedback;
import com.paper.auxiliary.entity.user.User_Info;
import com.paper.auxiliary.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("usermanage")
public class UserPageController {
    @Autowired
    private UserService userService;

    /*######################  普通用户管理相关  #######################*/
    @GetMapping("/info_user/{page}")
    public String infoUserPage(Model model,@PathVariable("page") Integer page){
        if(page<1) page = 1;
        final Integer items = SystemOption.items;
        Integer count = userService.getUserInfoCount();
        if((page-1)*items > count)
            page = (count-1)/items + 1;
        List<User_Info> userInfos = userService.getUserInfoScope((page-1)*items,items);

        model.addAttribute("page",page);
        model.addAttribute("pageCount",(count-1)/items + 1);
        model.addAttribute("iterPerPage",items);
        model.addAttribute("userInfoCount",count);
        model.addAttribute("userInfos",userInfos);
        return "info-user";
    }

    /*#################################################################*/
    /*#################################################################*/
    /*########################  公告管理相关  #########################*/
    /*#################################################################*/
    /*#################################################################*/
    @GetMapping(value = "/userfeedback/{page}",produces = "application/json;charset=utf-8")
    public String userFeedBackPage(Model model,@PathVariable("page") Integer page)
    {
        if(page<1) page = 1;
        final Integer items = SystemOption.items;
        Integer count = userService.getUserFeedbackCount();
        System.out.println(count);
        if((page-1)*items > count)
            page = (count-1)/items + 1;
        List<User_Feedback> user_feedbacks = userService.getUserFeedbackScope((page-1)*items,items);

        System.out.println(""+page +"  "+(count-1)/items);

        model.addAttribute("page",page);
        model.addAttribute("pageCount",(count-1)/items + 1);
        model.addAttribute("iterPerPage",items);
        model.addAttribute("userfeedbacksCount",count);
        model.addAttribute("userfeedbacks",user_feedbacks);

        return "user-feedback";
    }

    @GetMapping(value = "/userfeedback/show/{fid}")
    public String notificationAddPage(Model model,@PathVariable("fid") Integer fid)
    {
        model.addAttribute("userfeedback",userService.getUserFeedbackByFid(fid));
        return "user-feedback-show";
    }
}

