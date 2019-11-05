package com.paper.auxiliary.controller;

import com.paper.auxiliary.entity.manager.Admin_Info;
import com.paper.auxiliary.service.Paper.AuxiliaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("web/paper")
public class PaperPageController {
    @Autowired
    private AuxiliaryService auxiliaryService;

    @GetMapping("/theme")
    public String themePage()
    {
        return "wxFuncTheme";
    }

    @GetMapping("/similarity")
    public String similarityPage()
    {
        return "wxFuncSimilarity";
    }

    @GetMapping("/correction")
    public String correctionPage()
    {
        return "wxFuncCorrection";
    }
}
