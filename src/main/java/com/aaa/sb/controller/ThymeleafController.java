package com.aaa.sb.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:ThymeleafController
 * discription:
 * author:cmq
 * createTime:2018-12-06 14:51
 */
@Controller

public class ThymeleafController {
    @RequestMapping("/test")
    public  String testThymeleaf(Model model){
        //把数据存入model
        model.addAttribute("name","nice to meet you!!!");
        //返回test.html
        return "test";
    }


    @RequestMapping("/add")
    public  String add(){

        return "/user/add";
    }
    @RequestMapping("/update")
    public  String update(){

        return "/user/update";
    }
    @RequestMapping("/toLogin")
    public  String toLogin(){

        return "/login";
    }

    @RequestMapping("/unAuth")
    public  String unAuth(){

        return "/unAuth";
    }


    /**
     * 登陆逻辑处理
     */
    @RequestMapping("/login")
    public String login(String name,String password,Model model){
        /**
         * 使用shiro编写认证操作
         */
        //1。获取subject
        Subject subject=SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //3.执行登陆方法
        try {
            System.out.println("name="+name);
            subject.login(token);
            //登陆成功
            //跳转到test.html
            return "redirect:/test";
        } catch (UnknownAccountException e) {
            //e.printStackTrace();
            //登陆失败:用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e) {
            //e.printStackTrace();
            //登陆失败:密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }
}
