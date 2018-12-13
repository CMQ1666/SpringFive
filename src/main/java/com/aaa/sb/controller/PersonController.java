package com.aaa.sb.controller;

import com.aaa.sb.service.PersonService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * className:PersonController
 * discription:
 * author:Dbailing
 * createTime:2018-12-12 08:55
 */
@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    /**
     * 跳转人员转移列表页面
     * @return
     */
    @RequestMapping("/toPage")
    public String trans(){ return "/person/PersonTransfer" ;}
    /**
     *分页页面
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(personService.getList());
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;

        return  resultMap;

    }




}
