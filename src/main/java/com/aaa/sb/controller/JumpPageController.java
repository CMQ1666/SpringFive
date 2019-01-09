package com.aaa.sb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/Page")
public class JumpPageController {
    //前台首页
    @RequestMapping("/Home")
    public String ShouYe(){
        return "XxGjj/index";
    }
    //个人登录
    @RequestMapping("/denglu")
    public String DengLu(){
        return "XxGjj/login/denglu";
    }
    //个人登录进来
    @RequestMapping("/green")
    public String Green(){
        return "XxGjj/login/green";
    }
    //个人登录进来缴纳记录
    @RequestMapping("/jiaona")
    public String JiaoCun(){
        return "XxGjj/login/jiaona";
    }
    //个人登录进来贷款记录
    @RequestMapping("/daikuan")
    public String DaiKuan(){
        return "XxGjj/login/daikuan";
    }
    //个人还款
    @RequestMapping("/huankuan")
    public String huankuan(){
        return "XxGjj/login/huankuan";
    }
    //单位登录
    @RequestMapping("/danwei")
    public String DanWei(){
        return "qiantai/danwei";
    }
    //单位登录信息
    @RequestMapping("/dxinxi")
    public String DxinXi(){
        return "XxGjj/login/dxinxi";
    }
    //单位登录缴纳
    @RequestMapping("/danjiaona")
    public String DanJiaoNa(){
        return "XxGjj/login/danjiaona";
    }
    //单位登录缴纳记录
    @RequestMapping("/seldanjiao")
    public String DanJiaoNaJiLu(){
        return "XxGjj/login/danjiaonajilu";
    }
}
