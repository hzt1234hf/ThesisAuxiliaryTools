package com.paper.auxiliary.service.User;

import com.paper.auxiliary.entity.user.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface UserService {


    // ################ 用户信息操作 ################

    Integer getUserInfoCount();
    // 获取所有用户信息
    List<User_Info> getUserInfoAll();
    // 获取n到m的用户信息
    List<User_Info> getUserInfoScope(Integer index_1, Integer index_2);
    // 通过open ID获取用户信息
    User_Info getUserInfoByOid(String oid);
    // 增加用户信息
    User_Info addUserInfo(User_Info user_Info);
    // 更新用户信息
    User_Info updateUserInfo(User_Info user_Info);
    Integer updateUserInfoDate(String oid);
    // 删除用户信息
    void delUserInfo(String oid);

    // ################ 用户日志操作 ################
    // 增加指定用户日志
    User_Log addUserLog(User_Log ul);
    // 获取所有用户日志
    List<User_Log> getUserLogAll();
    // 获取指定用户的所有日志
    List<User_Log> getAllUserLogByOid(String oid);
    List<User_Log> getCountUserLogByOpenId(String oid);
    // 获取指定操作的所有日志
    List<User_Log> getUserLogByMid(Integer mid);

    User_Log getOneIsNotFinished();

    BigInteger getUserLogByModuleIdAndDate(Integer mid,String date);
    BigInteger getAllCountByDate(String date);
    BigInteger getAllCountFromDate(String date);
    List<Object[]> getUserLogByDate(String date);
    List<Object[]> getUserLogFromDate(String date);
    Integer getIsNotFinishedCount();

    Integer updateStatusByLogId(Integer lid);


    // 删除用户日志，使用日志id
    void delUserLogById(Integer lid);
    // 删除用户日志，使用用户id
    void delUserLogByOid(String oid);
    // 删除用户日志，使用操作id
    void delUserLogByMid(Integer mid);
    // 删除用户日志，使用用户id和操作id
    void delUserLogByOidAndMid(Integer oid, Integer mid);

    // ################ 用户反馈操作 ################
    List<User_Feedback> getUserFeedbackAll();
    int getUserFeedbackCount();
    List<User_Feedback> getUserFeedbackScope(Integer index_1, Integer index_2);
    User_Feedback getUserFeedbackByFid(Integer fid);
    User_Feedback addUserFeedback(User_Feedback user_feedback);
    void delUserFeedbackByFid(Integer fid);
    void delUserFeedbackByOpenid(Integer openid);
    int delUserFeedbackByFids(List<Integer> fids);

    // ################ 用户数据输入操作 ################
    // 获取所有输入
    List<User_Input> getUserInputAll();
    // 获取指定日志ID输入
    List<User_Input> getUserInputAllByLid(Integer logId);
    // 添加输入
    User_Input addUserInput(User_Input user_Input);
    // 删除输入，通过输入ID
    void delUserInputById(Integer id);
    // 删除输入，通过日志ID
    void delUserInputByLid(Integer logId);

    // ################ 用户数据输出操作 ################
    // 获取所有输出
    List<User_Output> getUserOutputAll();
    // 获取指定日志ID输出
    List<User_Output> getUserOutputAllByLid(Integer logId);
    // 添加输出
    User_Output addUserOutput(User_Output user_Output);
    // 删除输出，通过输出ID
    void delUserOutputById(Integer id);
    // 删除输出，通过日志ID
    void delUserOutputByLid(Integer logId);

    // ################ 用户分析操作 ################
    // 获取所有用户分析数据
    List<Analyse_User> getAnalyseUserAll();
    // 获取从Date至今的用户分析数据
//    List<Analyse_User> getAnalyseUserFromDate(String date,Integer count);
    List<Map<String,String>> getAnalyseUserFromDate(String date);
    // 获取指定id的用户分析数据
    Analyse_User getAnalyseUserAllById(Integer id);
    // 添加输出
    Analyse_User addAnalyseUser(Analyse_User analyse_user);
    void deleteAnalyseUserAll();
}
