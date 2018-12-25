package com.aaa.sb.controller;

import com.aaa.sb.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * className:LoanController
 * discription:
 * author:MVP
 * createTime:2018-12-10 15:04
 */
@Controller
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    /**
     * 根据个人账号查询个人信息
     * @param GRZH
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/{GRZH}")
    public Object getLoanUser(@PathVariable ("GRZH") String GRZH){

        return loanService.getList(GRZH);
    }

    /**
     * 跳转页面
     * @return
     */
    @RequestMapping("/toUser")
    public String  toUser(){
        return "loanApplication/application1";

    }

    /**
     * 跳转审核页面
     * @return
     */


    @RequestMapping("/wait")
    public String  toWait(){
        return "loanApplication/wait";}

    /**
     * 添加贷款信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody List<Map> map){
        loanService.addLoan(map);
       loanService.addRepay(map);
        return 1;


    }
}
