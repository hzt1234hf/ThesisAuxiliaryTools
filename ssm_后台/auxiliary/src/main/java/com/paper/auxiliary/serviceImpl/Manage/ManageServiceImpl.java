package com.paper.auxiliary.serviceImpl.Manage;

import com.paper.auxiliary.config.PerfAnalyseConfig;
import com.paper.auxiliary.entity.Table_Info;
import com.paper.auxiliary.entity.manager.*;
import com.paper.auxiliary.repository.Manage.*;
import com.paper.auxiliary.repository.Table_InfoRepository;
import com.paper.auxiliary.service.Manage.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {
    @Autowired
    private Admin_InfoRepository admin_infoRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private Analyse_FunctionRepository analyse_functionRepository;
    @Autowired
    private Analyse_PerformanceRepository analyse_performanceRepository;
    @Autowired
    private Module_InfoRepository module_InfoRepository;
    @Autowired
    private SettingRepository settingRepository;
    @Autowired
    private Table_InfoRepository table_infoRepository;
    @Autowired
    private PerfAnalyseConfig perfAnalyseConfig;

//    @Autowired
//    private PerformanceConfig performanceConfig;

    // ################ 通用操作 ################

    @Override
    public Boolean hasAdminInfo(String account, String password) {
        return admin_infoRepository.hasAdminInfo(account,password)>0;
    }

    @Override
    public Admin_Info getAdminInfoByAccountAndPassword(String account, String password) {
        return admin_infoRepository.getAdminInfoByAccountAndPassword(account,password);
    }

    @Override
    public int updateAdminInfoLoginInfo(String account) {
        return admin_infoRepository.updateAdminInfoLoginInfo(account);
    }

    @Override
    public int changeSettingValue(Integer id) {
        perfAnalyseConfig.changeMapValue(id);
        return settingRepository.changeSettingValue(id);
    }

    @Override
    public int setSettingValue(Integer id, Boolean val) {
        perfAnalyseConfig.setMapValue(id,val);
        return settingRepository.setSettingValue(id,val);
    }
    // ################ 数据库信息操作 ################

    @Override
    public List<Table_Info> getTableInfo(String tableName) {
        return table_infoRepository.getTableColumnInfo(tableName);
    }

    // ################ 管理员操作 ################

    @Override
    public Integer getAdminInfoCount() {
        return admin_infoRepository.getAdminInfoCount();
    }

    @Override
    public Integer getAdminInfoCountByAdminAccount(String adminAccount) {
        return admin_infoRepository.getAdminInfoCountByAdminAccount(adminAccount);
    }

    @Override
    public Integer getAdminInfoCountByAdminPassword(String adminAccount, String adminPassword) {
        return admin_infoRepository.getAdminInfoCountByAdminPassword(adminAccount,adminPassword);
    }

    @Override
    public List<Admin_Info> getAdminInfoAll() {
        return admin_infoRepository.findAdminInfoAll();
    }

    @Override
    public Admin_Info getAdminInfoById(String account) {
        return admin_infoRepository.findById(account).orElse(null);
    }

    @Override
    public List<Admin_Info> getAdminInfoScope(Integer index_1, Integer index_2) {
        return admin_infoRepository.getAdminInfoScope(index_1,index_2);
    }

    @Override
    public Admin_Info addAdminInfo(Admin_Info admin_Info) {
        return admin_infoRepository.save(admin_Info);
    }

    @Override
    public Admin_Info updateAdminInfo(Admin_Info admin_Info) {
        return admin_infoRepository.save(admin_Info);
    }

    @Override
    public Integer updateAdminInfo(String adminAccount, String adminName, String department, String position) {
        return admin_infoRepository.updateAdminInfo(adminAccount,adminName,department,position);
    }

    @Override
    public Integer updateAdminPassword(String adminAccount, String adminPassword) {
        return admin_infoRepository.updateAdminPassword(adminAccount,adminPassword);
    }

    @Override
    public Integer modifyAdminPermisContent(String adminAccount, Boolean val) {
        return admin_infoRepository.updateAdminPermisContent(adminAccount,val);
    }

    @Override
    public Integer modifyAdminPermisAnalyse(String adminAccount, Boolean val) {
        return admin_infoRepository.updateAdminPermisAnalyse(adminAccount,val);
    }

    @Override
    public Integer modifyAdminPermisUser(String adminAccount, Boolean val) {
        return admin_infoRepository.updateAdminPermisUser(adminAccount,val);
    }

    @Override
    public Integer modifyAdminPermisSetting(String adminAccount, Boolean val) {
        return admin_infoRepository.updateAdminPermisSetting(adminAccount,val);
    }

    @Override
    public void deleteAdminInfo(String account) {
        admin_infoRepository.deleteById(account);
    }

    @Override
    public int deleteAdminInfos(List<String> accounts) {
        return admin_infoRepository.deleteByAccounts(accounts);
    }

    // ################ 通知管理操作 ################

    @Override
    public List<Notification> getNotificationAll() {
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(Integer id) {
        return notificationRepository.findById(id).orElse(null);
    }

    @Override
    public int getNotificationCount() {
        return notificationRepository.getNotificationCount();
    }

    @Override
    public List<Notification> getNotificationScope(Integer index_1, Integer index_2) {
        return notificationRepository.getNotificationScope(index_1,index_2);
    }

    @Override
    public Notification addNotification(Notification notif) {
        return notificationRepository.save(notif);
    }

    @Override
    public Notification updateNotification(Notification notif) {
        return notificationRepository.save(notif);
    }

    @Override
    public int updateNotificationScrolling(Integer nid, Boolean val){
        return notificationRepository.changeNotificationScrolling(nid,val);
    }

    @Override
    public void deleteNotification(Integer id) {
        notificationRepository.deleteById(id);
    }

    @Override
    public int deleteNotifications(List<Integer> nids) {
        return notificationRepository.deleteNotificationsById(nids);
    }

    // ################ 功能分析操作 ################
    @Override
    public List<Analyse_Function> getAnalyseFunctionBetweenDate(Date start, Date end) {
        return analyse_functionRepository.getAnalyseFunctionBetweenDate(start,end);
    }

    @Override
    public Analyse_Function getAnalyseFunctionById(Integer id) {
        return analyse_functionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Analyse_Function> getAnalyseFunctionFromDate(String date, Integer count) {
        return analyse_functionRepository.findFromDate(date,count);
    }

    @Override
    public Analyse_Function addAnalyseFunction(Analyse_Function analyse_Function) {
        return analyse_functionRepository.save(analyse_Function);
    }

    @Override
    public Analyse_Function updateAnalyseFunction(Analyse_Function analyse_Function) {
        return analyse_functionRepository.save(analyse_Function);
    }

    @Override
    public void deleteAnalyseFunction(Integer id) {
        analyse_functionRepository.deleteById(id);
    }

    @Override
    public void deleteAnalyseFunctionByMid(Integer mid) {
        analyse_functionRepository.deleteByMoudleId(mid);
    }

    @Override
    public void deleteAnalyseFunctionBeforeDate(Date date) {
        analyse_functionRepository.deleteBeforeDate(date);
    }

    @Override
    public void deleteAnalyseFunctionAll() {
        analyse_functionRepository.deleteAll();
    }

    // ################ 性能分析操作 ################
    @Override
    public List<Analyse_Performance> getAnalysePerformanceBetweenDate(Date start, Date end) {
        return analyse_performanceRepository.getAnalysePerformanceBetweenDate(start,end);
    }

    @Override
    public Analyse_Performance getAnalysePerformanceById(Integer id) {
        return analyse_performanceRepository.findById(id).orElse(null);
    }
    @Override
    public List<Analyse_Performance> getAnalysePerformanceFromDate(String date,Integer count) {
        return analyse_performanceRepository.findFromDate(date,count);
    }
    @Override
    public Analyse_Performance addAnalysePerformance(Analyse_Performance analyse_Performance) {
        return analyse_performanceRepository.save(analyse_Performance);
    }

    @Override
    public Analyse_Performance updateAnalysePerformance(Analyse_Performance analyse_Performance) {
        return analyse_performanceRepository.save(analyse_Performance);
    }

    @Override
    public void deleteAnalysePerformance(Integer id) {
        analyse_performanceRepository.deleteById(id);
    }

    @Override
    public void deleteAnalysePerformanceBeforeDate(Date date) {
        analyse_performanceRepository.deleteBeforeDate(date);
    }

    @Override
    public void deleteAnalysePerformanceAll() {
        analyse_performanceRepository.deleteAll();
    }

    // ################ 功能模块操作 ################
    @Override
    public List<Module_Info> getModuleInfoAll() {
        return module_InfoRepository.findAll();
    }

    @Override
    public Module_Info getModuleInfoById(Integer id) {
        return module_InfoRepository.findById(id).orElse(null);
    }

    @Override
    public Module_Info addModuleInfo(Module_Info mod) {
        return module_InfoRepository.save(mod);
    }

    @Override
    public Module_Info updateModuleInfo(Module_Info mod) {
        return module_InfoRepository.save(mod);
    }

    @Override
    public void deleteModuleInfo(Integer id) {
        module_InfoRepository.deleteById(id);
    }

    @Override
    public int deleteModuleInfos(List<Integer> ids) {
        return module_InfoRepository.deleteByIds(ids);
    }
    // ################ 设置操作 ################

    @Override
    public List<Setting> getSettingAll() {
        return settingRepository.findAll();
    }

    @Override
    public Setting getSettingById(Integer id) {
        return settingRepository.findById(id).orElse(null);
    }

    @Override
    public Setting addSetting(Setting setting) {
        return settingRepository.save(setting);
    }

    @Override
    public Setting updateSetting(Setting setting) {
        return settingRepository.save(setting);
    }

    @Override
    public void deleteSetting(Integer id) {
        settingRepository.deleteById(id);
    }

    @Override
    public int deleteSettings(List<Integer> ids) {
        return settingRepository.deleteByIds(ids);
    }
}
