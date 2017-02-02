package com.cai.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by caibaolong on 2017/1/12.
 * 主要页面管理
 */
@Controller
@RequestMapping("/main")
public class MainController {
    //去主页面(网站首页)
    @RequestMapping(value = "/goMain.do")
    public String goMain() {
        return "/main/main";
    }

    //去用户登陆页面(包括注册)
    @RequestMapping(value = "/goUserLogin.do")
    public String goUserLogin() {
        return "user/user_login";
    }

    //去用户登陆后主页面(招聘系统)
    @RequestMapping(value = "/goUserMain.do")
    public String goHireMain() {
        return "user/user_main";
    }





    //去员工登陆页面
    @RequestMapping(value = "/goEmpLogin.do")
    public String goEmpLogin() {
        return "emp/emp_login";
    }

    //去员工主页面
    @RequestMapping(value = "/goEmpMain.do")
    public String goEmpMain() {
        return "/emp/emp_main";
    }

    //去管理员主页面
    @RequestMapping(value = "/goAdminMain.do")
    public String goAdminMain() {
        return "/emp/admin_main";
    }


}
