package com.paper.auxiliary.controller;

import org.mariadb.jdbc.internal.logging.Logger;
import org.mariadb.jdbc.internal.logging.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexPageController {

    private Logger logback = LoggerFactory.getLogger(IndexPageController.class);

    @RequestMapping("")
    public String rootPage(@CookieValue(name = "Login_Account", defaultValue = "") String cookie_login,
                           HttpServletResponse response,
                           HttpServletRequest request){
        return "redirect:/management/index";

    }
}
