package com.aaa.sb.controller.power;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * className:UserController
 * discription:
 * author:Dbailing
 * createTime:2018-12-06 11:48
 */
@Controller
@RequestMapping("/user")
public class  UserController {

    @RequestMapping("/update")
    public  String update(){

        return "/user/update";
    }

    @RequestMapping("/add")
    public  String add(){
        return "/user/add";
    }

    /**
     * 跳转到后台首页的方法
     * @return
     */
    @RequestMapping("/index")
    public  String list(){
        return "index";
    }

    /**
     * 跳转到登录界面
     * @return
     */
    @RequestMapping("/toLogin")
    public  String toLogin(){
        return "login";
    }

    /**
     * 登录逻辑处理 如果成功跳转到index页面，否则跳回登录页面
     * @param name
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public  String login(String name,String password,Model model){
        //使用shiro编写认证操作
        //1.获得subject
        System.out.println("name"+name);
        Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //3.执行登录方法
        try {
            subject.login(token);//登录成功
            return "redirect:/user/index";
        } catch (UnknownAccountException e) {
            // e.printStackTrace();
            //登录失败：用户名不存在
            model.addAttribute("msg","用户名不存在");
            return  "login";
        }catch (IncorrectCredentialsException e){
            // e.printStackTrace();
            //登录失败：密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }
    }
    @RequestMapping("/noAuth")
    public  String noAuth(){
        return "noAuth";
    }
}
