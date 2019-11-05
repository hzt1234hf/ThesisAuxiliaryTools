package com.paper.auxiliary.controller;

import com.paper.auxiliary.entity.manager.*;
import com.paper.auxiliary.entity.user.Analyse_User;
import com.paper.auxiliary.service.Manage.ManageService;
import com.paper.auxiliary.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.data.repository.query.Param;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private ManageService manageService;
    @Autowired
    private UserService userService;

    /**
     * String字符串转Date Bean函数
     * @param binder
     * @param request
     */
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // CustomDateEditor为自定义日期编辑器
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    // ################ 通用操作 ################

    @GetMapping(value = "/login", produces = "application/json;charset=utf-8")
    public String login(HttpServletResponse response,
                        HttpServletRequest request,
                        @CookieValue(name = "Login_Account", defaultValue = "") String login,
                        @RequestParam(value = "account", required = true) String account,
                        @RequestParam(value = "password", required = true) String password)
    {
        //System.out.println(request.getCookies().length);
//        System.out.println(login);
        Admin_Info admin_info = manageService.getAdminInfoByAccountAndPassword(account,password);
        if(!StringUtils.isEmpty(admin_info)) {
        //if(manageService.hasAdminInfo(account,password)) {
            Cookie cookie = new Cookie("Login_Account",account);
            cookie.setMaxAge(600);
            cookie.setPath("/");
            response.addCookie(cookie);
            request.getSession().setAttribute("Admin_Info",admin_info);
//            System.out.println(admin_info);

            manageService.updateAdminInfoLoginInfo(account);
            return "login return true";
        }
        else
            return "login return false";
    }

    @PostMapping("/hide")
    public void dataInit(@RequestParam(value = "biubiubiu",required = true) String biubiubiu)
    {
        if(!biubiubiu.equals("11263xv2rg")) return;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        manageService.deleteAnalyseFunctionAll();
        userService.deleteAnalyseUserAll();

        Date now = new Date();
        String nowDate = simpleDateFormat.format(now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH) - 14);

        for(;! simpleDateFormat.format(calendar.getTime()).equals(nowDate) ; calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH) + 1))
        {


            Date curDate = calendar.getTime();
            String curDateStr = simpleDateFormat.format(calendar.getTime());
            System.out.println(curDateStr + "    " + curDate);
            //  功能分析数据添加
            BigInteger count = BigInteger.ZERO;
            List<Module_Info> module_infos = manageService.getModuleInfoAll();
            for (Module_Info iter : module_infos) {
                count = userService.getUserLogByModuleIdAndDate(iter.getModuleId(), curDateStr);
                System.out.println("func  " + count);
                manageService.addAnalyseFunction(new Analyse_Function(iter.getModuleId(), count, curDate));
            }

            count = BigInteger.ZERO;
            //  用户分析数据添加
            count = userService.getAllCountByDate(simpleDateFormat.format(curDate));
            List<Object[]> list = userService.getUserLogByDate(curDateStr);

            for (Object[] iter : list) {
                BigInteger tmp = (BigInteger) iter[2];
                System.out.println("user  " + tmp);
                userService.addAnalyseUser(new Analyse_User((String) iter[0], (String) iter[1], tmp, curDate));
                count = count.subtract(tmp);
            }
            if (count.compareTo(BigInteger.ZERO) == 1) {
                userService.addAnalyseUser(new Analyse_User("null", null, count, curDate));
            }


        }
    }

    @RequestMapping(value = "/login2", produces = "application/json;charset=utf-8")
    public String login2(HttpServletResponse response,
                        HttpServletRequest request,
                        @CookieValue(value = "Login_Account", defaultValue = "") String login)
    {
        return "login2 return login: " + login + ", will return \"index\";";
    }

    @RequestMapping(value = "/logout", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public String logout(HttpServletResponse response,
                         HttpServletRequest request)
    {
        Cookie cookie = new Cookie("Login_Account","");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        request.getSession().invalidate();
        return "user logout" + request.getSession().getAttribute("Admin_Account");
    }

    @RequestMapping(value="/islogin")
    public Boolean isLogin(HttpServletResponse response,
                          HttpServletRequest request,
                          @CookieValue(name = "Login_Account", defaultValue = "") String login)
    {
        Admin_Info admin_info = (Admin_Info) request.getSession().getAttribute("Admin_Info");
        if(admin_info == null || login.equals(""))
            return false;
        return true;
    }


    // ################ 数据库信息操作 ################
    @RequestMapping("/tableInfo")
    public String getTableInfo(@RequestParam("tableName") String tableName)
    {
        return manageService.getTableInfo(tableName).toString();
    }

    // ################ 管理员操作 ################

    /**
     * 获取所有管理员信息
     * @return 管理员信息实例集合
     */
    @RequestMapping("/admininfo/get/all")
    public List<Admin_Info> getAdminInfoAll()
    {
        return manageService.getAdminInfoAll();
    }

    /**
     * 获取指定Id的管理员信息
     * @param account 管理员账号
     * @return 管理员实例
     */
    @RequestMapping("/admininfo/get/byid/{account}")
    public Admin_Info getAdminInfoById(@PathVariable("account") String account)
    {
        return manageService.getAdminInfoById(account);
    }

    @RequestMapping("/admininfo/count/byadminaccount/{adminaccount}")
    public Integer getAdminInfoCountByAdminAccount(@PathVariable("adminaccount") String account)
    {
        return manageService.getAdminInfoCountByAdminAccount(account);
    }

    /**
     * 增加管理员信息
     * @param admin_info 管理员信息实例
     * @return 增加的管理员信息实例
     */
    @RequestMapping("/admininfo/add")
    public String addAdminInfo(@RequestBody(required = true) Admin_Info admin_info)
    {
        if(manageService.addAdminInfo(admin_info) != null)
            return "添加成功";
        return "增加管理员出错！";
    }

    /**
     * 修改管理员信息
     * @param admin_info 管理员信息实例
     * @return 修改的管理员信息实例
     */
    @RequestMapping("/admininfo/modify")
    public String modifyAdminInfo(@RequestBody(required = true) Admin_Info admin_info)
    {
        if(manageService.getAdminInfoCountByAdminPassword(admin_info.getAdminAccount(),admin_info.getAdminPassword())<1)
        {
            return "密码错误！";
        }
        if(manageService.updateAdminInfo(admin_info.getAdminAccount(),admin_info.getAdminName(),admin_info.getDepartment(),admin_info.getPosition())>0)
            return "修改成功";
        return "未知错误！";
    }

    @RequestMapping("/admininfo/modify/password")
    public String modifyAdminPassword(@RequestBody(required = true) Admin_Info admin_info)
    {
        if(manageService.getAdminInfoCountByAdminPassword(admin_info.getAdminAccount(),admin_info.getAdminPassword())<1)
        {
            return "密码错误";
        }
        if(manageService.updateAdminPassword(admin_info.getAdminAccount(),admin_info.getNewPassword())>0)
            return "修改成功";
        return "出错了！";
    }

    @RequestMapping("/admininfo/modify/permission/content")
    public Integer modifyAdminPermisContent(@RequestParam(value = "adminAccount",required = true)String adminAccount, @RequestParam(value="val",required = true) Boolean val)
    {
        return manageService.modifyAdminPermisContent(adminAccount,val);
    }
    @RequestMapping("/admininfo/modify/permission/analyse")
    public Integer modifyAdminPermisAnalyse(@RequestParam(value = "adminAccount",required = true)String adminAccount, @RequestParam(value="val",required = true) Boolean val)
    {
        return manageService.modifyAdminPermisAnalyse(adminAccount,val);
    }
    @RequestMapping("/admininfo/modify/permission/user")
    public Integer modifyAdminPermisUser(@RequestParam(value = "adminAccount",required = true)String adminAccount, @RequestParam(value="val",required = true) Boolean val)
    {
        return manageService.modifyAdminPermisUser(adminAccount,val);
    }
    @RequestMapping("/admininfo/modify/permission/setting")
    Integer modifyAdminPermisSetting(@RequestParam(value = "adminAccount",required = true)String adminAccount, @RequestParam(value="val",required = true) Boolean val)
    {
        return manageService.modifyAdminPermisSetting(adminAccount,val);
    }
    /**
     * 删除指定Id的管理员信息
     * @param account 管理员账户
     */
    @RequestMapping("/admininfo/delete/byaccount/{account}")
    public void deleteAdminInfo(@PathVariable("account") String account)
    {
        manageService.deleteAdminInfo(account);
    }

    @RequestMapping("/admininfo/delete/byaccounts/")
    public void deleteAdminInfos(@RequestBody(required = true) List<String> adminAccounts)
    {
        manageService.deleteAdminInfos(adminAccounts);
    }

    // ################ 内容管理操作 ################
    @RequestMapping("/notification/get/all")
    public List<Notification> getNotificationAll()
    {
        return manageService.getNotificationAll();
    }

    @RequestMapping("/notification/get/bynid/{nid}")
    public Notification getNotificationByNid(@PathVariable("nid") Integer nid)
    {
        return manageService.getNotificationById(nid);
    }

    @RequestMapping("/notification/add")
    public Notification addNotification(@RequestBody(required = true) Notification notification)
    {
        return manageService.addNotification(notification);
    }
    @RequestMapping("/notification/modify")
    public Notification modifyNotification(@RequestBody(required = true) Notification notification)
    {
        return manageService.updateNotification(notification);
    }

    @RequestMapping("/notification/modify/scrolling/")
    public int modifyNotification(@RequestParam(value = "nid",required = true)Integer nid, @RequestParam(value="val",required = true) Boolean val)
    {
        return manageService.updateNotificationScrolling(nid,val);
    }

    @RequestMapping("/notification/delete/bynid/{nid}")
    public void deleteNotificationById(@PathVariable("nid") Integer nid)
    {
        manageService.deleteNotification(nid);
    }

    @RequestMapping("/notification/delete/bynids")
    public void deleteNotificationById(@RequestBody(required = true) List<Integer> nids)
    {
        manageService.deleteNotifications(nids);
    }

    // ################ 功能分析操作 ################

    /**
     * 获取指定时间段内的功能分析数据
     * @param start 开始时间
     * @param end 结束时间
     * @return 功能分析数据实例集合
     */
    @RequestMapping(value = "/analysefunction/get/between", produces = "application/json;charset=utf-8")
    public List<Analyse_Function> getAnalyseFunctionBetweenDate(@RequestParam(value = "start", required = true) Date start,
                                                         @RequestParam(value = "end", required = true) Date end)
    {
        return manageService.getAnalyseFunctionBetweenDate(start,end);
    }

    /**
     * 获取指定id的功能分析数据
     * @param id 功能分析数据id
     * @return 功能分析数据实例
     */
    @RequestMapping("/analysefunction/get/byid/{id}")
    public Analyse_Function getAnalyseFunctionById(@PathVariable("id") Integer id)
    {
        return manageService.getAnalyseFunctionById(id);
    }

    @RequestMapping("/analysefunction/get/bydate")
    public List<Analyse_Function> getAnalysefunctionByDate(@RequestBody Map<String,String> map)
    {
        return manageService.getAnalyseFunctionFromDate(map.get("date"),Integer.parseInt(map.get("count")));
    }

    /**
     * 增加功能分析数据
     * @param analyse_Function 功能分析数据实例
     * @return 增加的功能分析数据实例
     */
    @RequestMapping("/analysefunction/add")
    public Analyse_Function addAnalyseFunction(Analyse_Function analyse_Function)
    {
        return manageService.addAnalyseFunction(analyse_Function);
    }

    /**
     * 修改功能分析数据
     * @param analyse_Function 功能分析数据
     * @return 修改的功能分析数据
     */
    @RequestMapping("/analysefunction/modify")
    public Analyse_Function modifyAnalyseFunction(Analyse_Function analyse_Function)
    {
        return manageService.updateAnalyseFunction(analyse_Function);
    }

    /**
     * 删除指定id的功能分析数据
     * @param id 功能分析数据的id
     */
    @RequestMapping("/analysefunction/delete/byid/{id}")
    public void deleteAnalyseFunction(@PathVariable("id") Integer id)
    {
        manageService.deleteAnalyseFunction(id);
    }

    /**
     * 删除指定MoudleId模块的所有功能分析数据
     * @param mid 功能模块id
     */
    @RequestMapping("/analysefunction/delete/bymid/{mid}")
    public void deleteAnalyseFunctionByMid(@PathVariable("mid") Integer mid)
    {
        manageService.deleteAnalyseFunctionByMid(mid);
    }

    /**
     * 删除指定日期之前的所有功能分析数据
     * @param date 指定的日期
     */
    @RequestMapping(value = "/analysefunction/delete/beforedate", produces = "application/json;charset=utf-8")
    public void deleteAnalyseFunctionBeforeDate(@RequestParam(value = "date",required = true) Date date)
    //@RequestMapping("/analysefunction/delete/beforedate/{date}")
    //void deleteAnalyseFunctionBeforeDate(@PathVariable("date") Date date)
    {
        manageService.deleteAnalyseFunctionBeforeDate(date);
    }

    // ################ 性能分析操作 ################

    /**
     * 获取指定时间段内的性能分析数据
     * @param start 开始时间
     * @param end 结束时间
     * @return 性能分析数据实例集合
     */
    @RequestMapping(value = "/analyseperformance/get/between", produces = "application/json;charset=utf-8")
    public List<Analyse_Performance> getAnalysePerformanceBetweenDate(@RequestParam(value = "start", required = true) Date start,
                                                            @RequestParam(value = "end", required = true) Date end)
    {
        return manageService.getAnalysePerformanceBetweenDate(start,end);
    }

    /**
     * 获取指定id的性能分析数据
     * @param id 性能分析数据id
     * @return 性能分析数据实例
     */
    @RequestMapping("/analyseperformance/get/byid/{id}")
    public Analyse_Performance getAnalysePerformanceById(@PathVariable("id") Integer id)
    {
        return manageService.getAnalysePerformanceById(id);
    }

    @RequestMapping("/analyseperformance/get/bydate")
//    List<Analyse_Performance> getAnalysePerformanceByDate(@RequestParam("date") String date,@RequestParam("count") Integer count)
    public List<Analyse_Performance> getAnalysePerformanceByDate(@RequestBody Map<String,String> map)
    {
        List<Analyse_Performance> analyse_performances = manageService.getAnalysePerformanceFromDate(map.get("date"),Integer.parseInt(map.get("count")));
        return analyse_performances;
//        return manageService.getAnalysePerformanceByDate(date,count);
    }

    /**
     * 增加性能分析数据
     * @param analyse_performance 性能分析数据实例
     * @return 增加的性能分析数据实例
     */
    @RequestMapping("/analyseperformance/add")
    public Analyse_Performance addAnalysePerformance(Analyse_Performance analyse_performance)
    {
        return manageService.addAnalysePerformance(analyse_performance);
    }

    /**
     * 修改性能分析数据
     * @param analyse_performance 性能分析数据
     * @return 修改的性能分析数据
     */
    @RequestMapping("/analyseperformance/modify")
    public Analyse_Performance modifyAnalysePerformance(Analyse_Performance analyse_performance)
    {
        return manageService.updateAnalysePerformance(analyse_performance);
    }

    /**
     * 删除指定id的功能性能数据
     * @param id 功能分析数据的id
     */
    @RequestMapping("/analyseperformance/delete/byid/{id}")
    public void deleteAnalysePerformance(@PathVariable("id") Integer id)
    {
        manageService.deleteAnalysePerformance(id);
    }

    /**
     * 删除指定日期之前的所有性能分析数据
     * @param date 指定的日期
     */
    @RequestMapping(value = "/analyseperformance/delete/beforedate", produces = "application/json;charset=utf-8")
    public void deleteAnalysePerformanceBeforeDate(@RequestParam(value = "date",required = true) Date date)
    //@RequestMapping("/analysefunction/delete/beforedate/{date}")
    //void deleteAnalyseFunctionBeforeDate(@PathVariable("date") Date date)
    {
        manageService.deleteAnalysePerformanceBeforeDate(date);
    }

    // ################ 模块信息操作 ################
    /**
     * 获取所有模块信息
     * @return 模块信息实例列表
     */
    @RequestMapping("/moduleinfo/get/all")
    public List<Module_Info> getModuleInfoAll()
    {
        return manageService.getModuleInfoAll();
    }

    /**
     * 获取指定模块ID的模块信息
     * @param id 模块Id
     * @return 单个模块信息实例
     */
    @RequestMapping("/moduleinfo/get/byid/{id}")
    public Module_Info getModuleInfoById(@PathVariable("id") Integer id)
    {
        return manageService.getModuleInfoById(id);
    }

    /**
     * 增加新的模块信息
     * @param module_Info 新模块信息实例
     * @return 增加的模块信息实例
     */
    @RequestMapping("/moduleinfo/add")
    public Module_Info addModuleInfo(@RequestBody Module_Info module_Info,HttpServletRequest request)
    {
        return manageService.addModuleInfo(module_Info);
    }

    /**
     * 修改模块信息
     * @param module_Info 修改的模块信息
     * @return 修改的模块信息
     */
    @RequestMapping("/moduleinfo/modify")
    public Module_Info modifyModuleInfo(@RequestBody Module_Info module_Info, HttpServletRequest request)
    {
        return manageService.updateModuleInfo(module_Info);
    }

    /**
     * 删除指定模块Id的模块信息
     * @param id 模块Id
     */
    @RequestMapping("/moduleinfo/delete/byid/{id}")
    public void deleteModuleInfo(@PathVariable("id") Integer id)
    {
        manageService.deleteModuleInfo(id);
    }

    @RequestMapping("/moduleinfo/delete/byids/")
    public void deleteModuleInfos(@RequestBody(required = true) List<Integer> ids)
    {
        manageService.deleteModuleInfos(ids);
    }
    // ################ 设置操作 ################

    /**
     * 获取所有设置信息
     * @return 设置信息实例集合
     */
    @RequestMapping("/setting/get/all")
    public List<Setting> getSettingAll()
    {
        return manageService.getSettingAll();
    }

    /**
     * 获取指定id的设置信息
     * @param id 设置信息的id
     * @return 设置信息实例
     */
    @RequestMapping("/setting/get/byid/{id}")
    public Setting getSettingById(@PathVariable("id") Integer id)
    {
        return manageService.getSettingById(id);
    }

    /**
     * 增加设置信息
     * @param setting 设置信息实例
     * @return 增加的设置信息实例
     */
    @RequestMapping("/setting/add")
    public Setting addSetting(@RequestBody Setting setting)
    {
        return manageService.addSetting(setting);
    }

    /**
     * 修改设置信息
     * @param setting 设置信息实例
     * @return 修改的设置信息实例
     */
    @RequestMapping("/setting/modify")
    public Setting modifySetting(@RequestBody Setting setting)
    {
        return manageService.updateSetting(setting);
    }

    @RequestMapping("/setting/modify/byid/{id}")
    public int changeSettingValue(@PathVariable("id") Integer id)
    {
        return manageService.changeSettingValue(id);
    }

    @RequestMapping("/setting/modify/byid")
    public int setSettingValue(@RequestParam(value = "id",required = true) Integer id, @RequestParam(value = "val",required = true) Boolean val)
    {
        return manageService.setSettingValue(id,val);
    }

    /**
     * 删除指定的设置信息
     * @param id 设置信息id
     */
    @RequestMapping("/setting/delete/byid/{id}")
    public void deleteSetting(@PathVariable("id") Integer id)
    {
        manageService.deleteSetting(id);
    }

    @RequestMapping("/setting/delete/byids/")
    public void deleteSettings(@RequestBody(required = true) List<Integer> ids)
    {
        manageService.deleteSettings(ids);
    }

}
