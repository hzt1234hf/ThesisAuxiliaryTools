package com.paper.auxiliary.controller;

import com.paper.auxiliary.entity.manager.Module_Info;
import com.paper.auxiliary.entity.user.*;
import com.paper.auxiliary.service.Manage.ManageService;
import com.paper.auxiliary.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ManageService manageService;

    // ################ 通用操作 ################
    @RequestMapping("/userinoutdata/get/bylid/{lid}")
    public Map<String,String> getUserInOutData(@PathVariable("lid") Integer logId)
    {
        Map<String,String> map = new HashMap<String,String>();
        map.put("logId",String.valueOf(logId));
        List<User_Input> user_inputs = userService.getUserInputAllByLid(logId);
        String inData = new String();
        for(int i = 0;i<user_inputs.size();i++)
        {
            if(user_inputs.size()>1)
            {
                inData+="输入数据" + (i+1) + "： ";
            }
            inData += user_inputs.get(i).getInData() + "\n";
//            map.put("inData" + (i+1),inData);
        }
        map.put("inData",inData);

        List<User_Output> user_outputs = userService.getUserOutputAllByLid(logId);
        for(int i = 0;i<user_outputs.size();i++)
        {
//            map.put("outData"+(i+1),user_outputs.get(i).getOutData());
//            map.put("date"+(i+1),user_outputs.get(i).getDate().toString());
            map.put("outData",user_outputs.get(i).getOutData());
            map.put("date",user_outputs.get(i).getDate().toString());
        }
        return map;
    }
    // ################ 用户信息操作 ################
    /**
     * 获取所用用户信息
     * @return 用户信息实例列表
     */
    @RequestMapping("/userinfo/get/all")
    public List<User_Info> getUserInfoAll()
    {
        return userService.getUserInfoAll();
    }

    /**
     * 获取M到N的用户信息
     * @param index1 开始索引
     * @param index2 结束索引
     * @return 用户信息实例列表
     */
    @RequestMapping("/userinfo/get/between/{index1}to{index2}")
    public List<User_Info> getUserInfoPartial(@PathVariable("index1") Integer index1,@PathVariable("index2") Integer index2)
    {
        return userService.getUserInfoScope(index1,index2);
    }
    /**
     * 获取指定OpenID的用户的信息
     * @param oid 用户OpenId
     * @return 单个对象
     */
    @RequestMapping("/userinfo/get/byid/{oid}")
    public User_Info getUserInfoByOpenid(@PathVariable("oid") String oid)
    {
        return userService.getUserInfoByOid(oid);
    }

    /**
     * 增加新的用户
     * @param user_info 用户信息实例
     * @return
     */
    @RequestMapping("/userinfo/add")
    public User_Info addUserInfo(@RequestBody User_Info user_info)
    {
        return userService.addUserInfo(user_info);
    }

    /**
     * 修改用户信息
     * @param user_info 用户信息实例
     * @return 修改后的用户信息实例
     */
    @RequestMapping("/userinfo/modify")
    public User_Info modifyUserInfo(User_Info user_info)
    {
        return userService.updateUserInfo(user_info);
    }

    /**
     * 删除指定OpenId用户的信息
     * @param oid 用户OpenId
     */
    @RequestMapping("/userinfo/delete/byid/{oid}")
    public void deleteUserInfo(@PathVariable("oid") String oid)
    {
        userService.delUserInfo(oid);
    }

    // ################ 用户日志操作 ################

    /**
     * 添加日志信息
     * @param user_Log 日志信息实例，不包含id值
     * @return 添加的日志信息，包含id值
     */
    @RequestMapping("/userlog/add")
    public User_Log addUserLog(User_Log user_Log)
    {
        return userService.addUserLog(user_Log);
    }

    /**
     * 获取所用日志信息
     * @return 日志信息实例
     */
    @RequestMapping("/userlog/get/all")
    public List<User_Log> getUserLogAll()
    {
        return userService.getUserLogAll();
    }

    /**
     * 通过openid获取相应用户的日志信息
     * @param oid 用户的OpenId
     * @return 日志信息列表
     */
    @GetMapping("/userlog/get/byoid/{oid}")
//    List<User_Log> getUserLogByOid(@PathVariable("oid") String oid)
    public List<Map<String,String>> getUserLogByOid(@PathVariable("oid") String oid)
    {
//        return userService.getAllUserLogByOid(oid);
        List<Module_Info> module_infos = manageService.getModuleInfoAll();
        Map<Integer,String> module_name = new HashMap<Integer,String>();
        for (Module_Info iter :module_infos) {
            module_name.put(iter.getModuleId(),iter.getName());
        }
        List<User_Log> user_logs =  userService.getAllUserLogByOid(oid);
        List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
        Integer index = 1;
        for(User_Log iter : user_logs)
        {
            Map<String,String> tmp = new HashMap<String,String>();
            tmp.put("logId",index.toString());
//            tmp.put("moudleId",iter.getModuleId().toString());
            tmp.put("moudleName",module_name.get(iter.getModuleId()));
            tmp.put("isFinished",iter.getIsFinished()?"已完成":"待处理");

            tmp.put("logDate",iter.getLogDate().toString());

            mapList.add(tmp);
            index++;
        }
        return mapList;
    }
    @GetMapping("/userlog/get/count/byoid/{oid}")
    public List<Map<String,String>> getCountUserLogByOid(@PathVariable("oid") String oid)
    {
//        return userService.getCountUserLogByOpenId(oid);
        List<Module_Info> module_infos = manageService.getModuleInfoAll();
        Map<Integer,String> module_name = new HashMap<Integer,String>();
        for (Module_Info iter :module_infos) {
            module_name.put(iter.getModuleId(),iter.getName());
        }
        List<User_Log> user_logs =  userService.getCountUserLogByOpenId(oid);
        List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
        for(User_Log iter : user_logs)
        {
            Map<String,String> tmp = new HashMap<String,String>();
            tmp.put("logId",iter.getLogId().toString());
            tmp.put("moudleName",module_name.get(iter.getModuleId()));
            tmp.put("isFinished",iter.getIsFinished()?"1":"0");
            tmp.put("logDate",iter.getLogDate().toString());
            mapList.add(tmp);
        }
        return mapList;
    }


    @RequestMapping("/userlog/get/notfinished")
    public Integer getIsNotFinishedCount()
    {
        return userService.getIsNotFinishedCount();
    }

    /**
     * 通过功能id获取相应功能的日志信息
     * @param mid 功能id
     * @return 日志信息列表
     */
    @RequestMapping("/userlog/get/bymidanddate")
    public BigInteger getUserLogByMidAndDate(@RequestParam("moudleId") Integer mid, @RequestParam("date") String date)
    {
        return userService.getUserLogByModuleIdAndDate(mid,date);
    }

    @RequestMapping("/userlog/get/bymid/{mid}")
    public List<User_Log> getUserLogByMid(@PathVariable("mid") Integer mid)
    {
        return userService.getUserLogByMid(mid);
    }

    /**
     * 删除指定logid的日志
     * @param lid 日志的logid
     */
    @RequestMapping("/userlog/delete/byid/{id}")
    public void delUserLogById(@PathVariable("id") Integer lid)
    {
        userService.delUserLogById(lid);
    }

    /**
     * 删除指定OpenId用户的日志
     * @param oid 用户的Openid
     */
    @RequestMapping("/userlog/delete/byoid/{oid}")
    public void delUserLogByOid(@PathVariable("oid") String oid)
    {
        userService.delUserLogByOid(oid);
    }

    /**
     * 删除指定MoudleId模块的所有日志
     * @param mid 模块的MoudleId
     */
    @RequestMapping("/userlog/delete/bymid/{mid}")
    public void delUserLogByMid(@PathVariable("mid") Integer mid)
    {
        userService.delUserLogByMid(mid);
    }

    /**
     * 删除指定OpenId用户的指定MoudleId模块的所有日志
     * @param oid 用户的OpenId
     * @param mid 模块的MoudleId
     */
    @RequestMapping("/userlog/delete/byoidandmid/{oid}and{mid}")
    public void delUserLogByMid(@PathVariable("oid") Integer oid, @PathVariable("mid") Integer mid)
    {
        userService.delUserLogByOidAndMid(oid,mid);
    }

    // ################ 用户反馈操作 ################
    @RequestMapping("/userfeedback/delete/byfid/{fid}")
    public void deleteUserFeedbackByFid(@PathVariable("fid") Integer fid)
    {
        userService.delUserFeedbackByFid(fid);
    }

    @RequestMapping("/userfeedback/delete/byfids/")
    public void deleteUserFeedbackByFids(@RequestBody(required = true) List<Integer> fids)
    {
        userService.delUserFeedbackByFids(fids);
    }

    @RequestMapping("/userfeedback/add")
    public User_Feedback addUserFeedback(User_Feedback user_feedback)
    {
        return userService.addUserFeedback(user_feedback);
    }

    // ################ 用户输入操作 ################

    /**
     * 获取用户所有输入
     * @return 用户输入数据实例集合
     */
    @RequestMapping("/userinput/get/all")
    public List<User_Input> getUserInputAll()
    {
        return userService.getUserInputAll();
    }

    /**
     * 公共接口！！！
     * 通过日志id获取输入数据实例
     * @param logId 日志id
     * @return 用户输入数据实例集合
     */
    @GetMapping("/userinput/get/bylid/{lid}")
    public List<User_Input> getUserInputByLid(@PathVariable("lid") Integer logId)
    {
        return userService.getUserInputAllByLid(logId);
    }

    /**
     * 增加用户输入
     * @param user_Input 用户输入数据实例
     * @return 增加的用户输入数据实例
     */
    @RequestMapping("/userinput/add")
    public User_Input addUserInput(User_Input user_Input)
    {
        return userService.addUserInput(user_Input);
    }

    /**
     * 通过id删除用户输入数据
     * @param id 用户输入数据id
     */
    @RequestMapping("/userinput/delete/byid/{id}")
    public void delUserInputById(@PathVariable("id") Integer id)
    {
        userService.delUserInputById(id);
    }

    /**
     * 通过日志id删除用户输入实例
     * @param lid 日志id
     */
    @RequestMapping("/userinput/delete/bylid/{lid}")
    public void delUserInputByLid(@PathVariable("lid") Integer lid)
    {
        userService.delUserInputByLid(lid);
    }

    // ################ 用户输出操作 ################

    /**
     * 获取用户所有输出
     * @return 用户输出数据实例集合
     */
    @RequestMapping("/useroutput/get/all")
    public List<User_Output> getUserOutputAll()
    {
        return userService.getUserOutputAll();
    }

    /**
     * 公共接口
     * 通过日志id获取输出数据实例
     * @param logId 日志id
     * @return 用户输出数据实例集合
     */
    @GetMapping("/useroutput/get/bylid/{lid}")
    public List<User_Output> getUserOutputByLid(@PathVariable("lid") Integer logId)
    {
        return userService.getUserOutputAllByLid(logId);
    }

    /**
     * 增加用户输出
     * @param user_Output 用户输出数据实例
     * @return 增加的用户输出数据实例
     */
    @RequestMapping("/useroutput/add")
    public User_Output addUserOutput(User_Output user_Output)
    {
        return userService.addUserOutput(user_Output);
    }
    /**
     * 通过id删除用户输出数据
     * @param id 用户输出数据id
     */
    @RequestMapping("/useroutput/delete/byid/{id}")
    public void delUserOutputById(@PathVariable("id") Integer id)
    {
        userService.delUserOutputById(id);
    }

    /**
     * 通过日志id删除用户输出实例
     * @param lid 日志id
     */
    @RequestMapping("/useroutput/delete/bylid/{lid}")
    public void delUserOutputByLid(@PathVariable("lid") Integer lid)
    {
        userService.delUserOutputByLid(lid);
    }

    // ################ 用户分析操作 ################

    @RequestMapping("/analyseuser/get/bydate")
//    List<Analyse_Performance> getAnalysePerformanceByDate(@RequestParam("date") String date,@RequestParam("count") Integer count)
    public List<Map<String,String>> getAnalyseUserByDate(@RequestBody Map<String,String> map)
    {
        List<Map<String,String>> mapList = userService.getAnalyseUserFromDate(map.get("date"));
        return mapList;
//        return manageService.getAnalysePerformanceByDate(date,count);
    }
}
