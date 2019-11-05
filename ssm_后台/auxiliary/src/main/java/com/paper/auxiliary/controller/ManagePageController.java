package com.paper.auxiliary.controller;

import com.paper.auxiliary.config.RemoteInfo;
import com.paper.auxiliary.config.SystemInfo;
import com.paper.auxiliary.config.SystemOption;
import com.paper.auxiliary.entity.Table_Info;
import com.paper.auxiliary.entity.manager.Admin_Info;
import com.paper.auxiliary.entity.manager.Module_Info;
import com.paper.auxiliary.entity.manager.Notification;
import com.paper.auxiliary.entity.manager.Setting;
import com.paper.auxiliary.entity.user.User_Info;
import com.paper.auxiliary.service.Manage.ManageService;
import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("management")
public class ManagePageController {

    @Autowired
    private ManageService manageService;

    private Logger logback = LoggerFactory.getLogger(ManagePageController.class);
    /*########################  主页面相关  #########################*/

    /**
     * 获取登录页面
     * @param cookie_login 用户登录Cookie
     * @param response
     * @param request
     * @return 返回结果指向页面，已登录则进入首页，未登录则停留登录界面
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model,
                            @CookieValue(name = "Login_Account", defaultValue = "") String cookie_login,
                            HttpServletResponse response,
                            HttpServletRequest request)
    {
        /**
         * 任何一方丢失数据必然重新登录，并清空数据
         * 不这么处理将造成循环重定向！
         */
        int num = (int) (Math.random() * 11);
        model.addAttribute("bgimgNum",num);
        if(cookie_login.equals("")) {
            request.getSession().invalidate();  // session失效
            return "login";
        }
        else if(request.getSession().getAttribute("Admin_Info") == null) {
            Cookie cookie = new Cookie("Login_Account","");
            cookie.setMaxAge(0);                // cookie立即过期
            cookie.setPath("/");                // 设置路径
            response.addCookie(cookie);         // 修改cookie
            return "login";
        }
        return "redirect:index";
    }

    /**
     * 登录结果提交
     * @param response
     * @param request
     * @param account 用户名
     * @param password 密码
     * @return 返回结果指向页面，登录成功进入首页，失败停留登录界面
     */
    @RequestMapping(value = "/login", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ModelAndView login(HttpServletResponse response,
                              HttpServletRequest request,
                              @RequestParam(value = "account", required = true) String account,
                              @RequestParam(value = "password", required = true) String password)
    {
/*        System.out.println(password);
        return new ModelAndView("login");*/
        Admin_Info admin_info = manageService.getAdminInfoByAccountAndPassword(account,password);
        if(!StringUtils.isEmpty(admin_info)) {
        //if(manageService.hasAdminInfo(account,password)) {
            Cookie cookie = new Cookie("Login_Account",account);
            cookie.setMaxAge(600);      // 10分钟后过期
            cookie.setPath("/");        // 设置路径
            response.addCookie(cookie); // 添加cookie
            request.getSession().setAttribute("Admin_Info",admin_info);

            manageService.updateAdminInfoLoginInfo(account);
            return new ModelAndView("redirect:index");
        }
        else {
            return new ModelAndView("login");
        }
    }

    /**
     * 登出请求，清空session和cookie
     * @param response
     * @param request
     * @return 返回登录界面
     */
    @RequestMapping(value = "/logout", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public String logout(HttpServletResponse response,
                         HttpServletRequest request)
    {
        Cookie cookie = new Cookie("Login_Account","");
        cookie.setMaxAge(0);                // cookie立即过期
        cookie.setPath("/");                // 设置路径
        response.addCookie(cookie);         // 修改cookie
        request.getSession().invalidate();  // session失效
        return "redirect:login";
    }


    /**
     * 获取主页面
     * @param model
     * @return 主界面
     */
    @GetMapping("/index")
    public String indexPage(Model model,
                            HttpServletResponse response,
                            HttpServletRequest request)
    {
        Admin_Info admin_info = (Admin_Info) request.getSession().getAttribute("Admin_Info");
        model.addAttribute("admin_info",admin_info);
        return "index";
    }

    /**
     * 获取欢迎界面
     * @param model
     * @return 欢迎页面
     */
    @GetMapping("/welcome")
    public String welcomePage(Model model)
    {
        return "welcome";
    }

    /**
     * 获取主面板页面
     * @param model
     * @param response
     * @param request
     * @return 主面板页面
     */
    @GetMapping("/main_panel")
    public String mainPanelPage(Model model,
                            HttpServletResponse response,
                            HttpServletRequest request)
    {
        model.addAttribute("reqinfo", RemoteInfo.getInstance(request));
        model.addAttribute("sysinfo", SystemInfo.getInstance().getInfomap());
        return "main-panel";
    }

/*  // 通用方法
    @GetMapping(value = "/setting_system/add")
    public String settingSystemAddPage(Model model)
    {
        model.addAttribute("tableColumn",manageService.getTableInfo("settings"));
        model.addAttribute("postAddr","add");
        return "data-add";
    }

    @PostMapping(value = "/setting_system/add")
    public String settingSystemAdd(Model model, Setting setting)
    {
        System.out.println(setting);
        return "redirect:setting_system";
    }

    @GetMapping(value = "/setting_system/edit/{id}",produces = "application/json;charset=utf-8")
    public String settingSystemEdit(Model model,@PathVariable("id") Integer id)
    {
        return "data-edit";
    }*/
    /*#################################################################*/
    /*#################################################################*/
    /*########################  公告管理相关  #########################*/
    /*#################################################################*/
    /*#################################################################*/
    @GetMapping(value = "/notification/{page}",produces = "application/json;charset=utf-8")
    public String notificationPage(Model model,@PathVariable("page") Integer page)
    {
        if(page<1) page = 1;
        final Integer items = SystemOption.items;
        Integer count = manageService.getNotificationCount();
        if((page-1)*items > count)
            page = (count-1)/items + 1;
        List<Notification> notifications = manageService.getNotificationScope((page-1)*items,items);

        System.out.println(""+page +"  "+(count-1)/items);

        model.addAttribute("page",page);
        model.addAttribute("pageCount",(count-1)/items + 1);
        model.addAttribute("iterPerPage",items);
        model.addAttribute("notificationsCount",count);
        model.addAttribute("notifications",notifications);

        return "notification";
    }
    @GetMapping(value = "/notification/add")
    public String notificationAddPage(Model model)
    {
        model.addAttribute("notification",new Notification());
        return "notification-add";
    }

    @GetMapping(value = "/notification/edit/info/{noticeid}",produces = "application/json;charset=utf-8")
    public String notificationEditPage(Model model,@PathVariable("noticeid") Integer noticeid)
    {
        model.addAttribute("notification",manageService.getNotificationById(noticeid));
        return "notification-add";
    }

    /*#################################################################*/
    /*#################################################################*/
    /*########################  系统分析相关  #########################*/
    /*#################################################################*/
    /*#################################################################*/
    @GetMapping(value = "/analyse_function")
    public String analyseFunctionPage(Model model)
    {
        return "analyse-function";
    }

    @GetMapping(value = "/analyse_performance")
    public String analysePerformancePage(Model model)
    {
        return "analyse-performance";
    }

    @GetMapping(value = "/analyse_user")
    public String analyseUserPage(Model model)
    {
        return "analyse-user";
    }

    /*#################################################################*/
    /*#################################################################*/
    /*########################  用户管理相关  #########################*/
    /*#################################################################*/
    /*#################################################################*/



    /*######################  系统用户管理相关  #######################*/
    @GetMapping("/info_admin/{page}")
    public String infoAdminPage(Model model,@PathVariable("page") Integer page){
        if(page<1) page = 1;
        final Integer items = SystemOption.items;
        Integer count = manageService.getAdminInfoCount();
        if((page-1)*items > count)
            page = (count-1)/items + 1;
        List<Admin_Info> admin_infos = manageService.getAdminInfoScope((page-1)*items,items);

        System.out.println(""+page +"  "+(count-1)/items);

        model.addAttribute("page",page);
        model.addAttribute("pageCount",(count-1)/items + 1);
        model.addAttribute("iterPerPage",items);
        model.addAttribute("adminInfoCount",count);
        model.addAttribute("admin_infos",admin_infos);
        return "info-admin";
    }

    @GetMapping(value = "/info_admin/add")
    public String infoAdminAddPage(Model model)
    {
        model.addAttribute("adminInfo",new Admin_Info());
        return "info-admin-add";
    }

    @GetMapping(value = "/info_admin/edit/info/{account}",produces = "application/json;charset=utf-8")
    public String infoAdminEditPage(Model model,@PathVariable("account") String account)
    {
        model.addAttribute("adminInfo",manageService.getAdminInfoById(account));
        return "info-admin-add";
    }

    @GetMapping(value = "/info_admin/edit/password/{account}",produces = "application/json;charset=utf-8")
    public String infoAdminEditPasswordPage(Model model,@PathVariable("account") String account)
    {
        model.addAttribute("adminInfo",manageService.getAdminInfoById(account));
        return "info-admin-modify-password";
    }

    /*#################################################################*/
    /*#################################################################*/
    /*##########################  设置相关  ###########################*/
    /*#################################################################*/
    /*#################################################################*/

    /*########################  模块设置相关  #########################*/

    /**
     * 获取模块设置页面
     * @param model
     * @return 页面
     */
    @GetMapping("/setting_module")
    public String settingModulePage(Model model){
        List<Module_Info> module_infos = manageService.getModuleInfoAll();
        model.addAttribute("setmod", module_infos);
        return "setting-module";
    }

    /**
     * 获取添加模块页面
     * @param model
     * @return 添加模块页面
     */
    @GetMapping(value = "/setting_module/add")
    public String settingModuleAddPage(Model model)
    {
        model.addAttribute("setmod",new Module_Info());
        return "setting-module-add";
    }

    /**
     * 获取编辑模块页面
     * @param model
     * @param id 编辑的模块ID
     * @return 模块编辑页面
     */
    @GetMapping(value = "/setting_module/edit/{id}",produces = "application/json;charset=utf-8")
    public String settingModuleEditPage(Model model,@PathVariable("id") Integer id)
    {
        model.addAttribute("setmod",manageService.getModuleInfoById(id));
        return "setting-module-add";
    }

    /*########################  系统设置相关  #########################*/

    /**
     * 获取系统设置页面
     * @param model
     * @return 系统设置页面
     */
    @GetMapping("/setting_system")
    public String settingSystemPage(Model model){
        List<Setting> settings = manageService.getSettingAll();
        model.addAttribute("setsys", settings);
        return "setting-system";
    }

    /**
     * 获取增加系统设置页面
     * @param model
     * @return 系统设置页面
     */
    @GetMapping(value = "/setting_system/add")
    public String settingSystemAddPage(Model model)
    {
        model.addAttribute("setsys",new Setting());
        return "setting-system-add";
    }

    /**
     * 获取编辑系统设置页面
     * @param model
     * @param id 系统设置ID
     * @return 系统设置编辑页面
     */
    @GetMapping(value = "/setting_system/edit/{id}",produces = "application/json;charset=utf-8")
    public String settingSystemEditPage(Model model,@PathVariable("id") Integer id)
    {
        model.addAttribute("setsys",manageService.getSettingById(id));
        return "setting-system-add";
    }

}
