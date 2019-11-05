package com.paper.auxiliary.service.Manage;

import com.paper.auxiliary.entity.Table_Info;
import com.paper.auxiliary.entity.manager.*;

import java.util.Date;
import java.util.List;

public interface ManageService {
    // ################ 通用操作 ################
    Boolean hasAdminInfo(String account, String password);
    Admin_Info getAdminInfoByAccountAndPassword(String account, String password);
    int updateAdminInfoLoginInfo(String account);
    int changeSettingValue(Integer id);
    int setSettingValue(Integer id, Boolean val);

    // ################ 数据库信息操作 ################
    List<Table_Info> getTableInfo(String tableName);

    // ################ 管理员信息操作 ################
    Integer getAdminInfoCount();
    Integer getAdminInfoCountByAdminAccount(String adminAccount);
    Integer getAdminInfoCountByAdminPassword(String adminAccount, String adminPassword);
    List<Admin_Info> getAdminInfoAll();
    Admin_Info getAdminInfoById(String account);
    // 获取n到m的用户信息
    List<Admin_Info> getAdminInfoScope(Integer index_1, Integer index_2);
    Admin_Info addAdminInfo(Admin_Info admin_Info);
    Admin_Info updateAdminInfo(Admin_Info admin_Info);
    Integer updateAdminInfo(String adminAccount,String adminName,String department,String position);
    Integer updateAdminPassword(String adminAccount,String adminPassword);
    Integer modifyAdminPermisContent(String adminAccount, Boolean val);
    Integer modifyAdminPermisAnalyse(String adminAccount, Boolean val);
    Integer modifyAdminPermisUser(String adminAccount, Boolean val);
    Integer modifyAdminPermisSetting(String adminAccount, Boolean val);
    void deleteAdminInfo(String account);
    int deleteAdminInfos(List<String> accounts);

    // ################ 通知管理操作 ################
    List<Notification> getNotificationAll();
    Notification getNotificationById(Integer id);
    int getNotificationCount();
    List<Notification> getNotificationScope(Integer index_1, Integer index_2);
    Notification addNotification(Notification notif);
    Notification updateNotification(Notification notif);
    int updateNotificationScrolling(Integer nid, Boolean val);
    void deleteNotification(Integer id);
    int deleteNotifications(List<Integer> nids);

    // ################ 功能分析操作 ################
    //List<Analyse_Function> getAnalyseFunctionAll();
    List<Analyse_Function> getAnalyseFunctionBetweenDate(Date start, Date end);
    Analyse_Function getAnalyseFunctionById(Integer id);
    List<Analyse_Function> getAnalyseFunctionFromDate(String date,Integer count);
    Analyse_Function addAnalyseFunction(Analyse_Function analyse_Function);
    Analyse_Function updateAnalyseFunction(Analyse_Function analyse_Function);
    void deleteAnalyseFunction(Integer id);
    void deleteAnalyseFunctionByMid(Integer mid);
    void deleteAnalyseFunctionBeforeDate(Date date);
    void deleteAnalyseFunctionAll();

    // ################ 性能分析操作 ################
    //List<Analyse_Performance> getAnalysePerformanceAll();
    List<Analyse_Performance> getAnalysePerformanceBetweenDate(Date start, Date end);
    List<Analyse_Performance> getAnalysePerformanceFromDate(String date,Integer count);
    Analyse_Performance getAnalysePerformanceById(Integer id);
    Analyse_Performance addAnalysePerformance(Analyse_Performance analyse_Performance);
    Analyse_Performance updateAnalysePerformance(Analyse_Performance analyse_Performance);
    void deleteAnalysePerformance(Integer id);
    void deleteAnalysePerformanceBeforeDate(Date date);
    void deleteAnalysePerformanceAll();

    // ################ 功能模块操作 ################
    List<Module_Info> getModuleInfoAll();
    Module_Info getModuleInfoById(Integer id);
    Module_Info addModuleInfo(Module_Info mod);
    Module_Info updateModuleInfo(Module_Info mod);
    void deleteModuleInfo(Integer id);
    int deleteModuleInfos(List<Integer> ids);

    // ################ 设置操作 ################
    List<Setting> getSettingAll();
    Setting getSettingById(Integer id);
    Setting addSetting(Setting setting);
    Setting updateSetting(Setting setting);
    void deleteSetting(Integer id);
    int deleteSettings(List<Integer> ids);

}
