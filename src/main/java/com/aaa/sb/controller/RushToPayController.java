package com.aaa.sb.controller;

import com.aaa.sb.service.RushToPayServcie;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:RushToPayController
 * discription:
 * author:zz
 * createTime:2018-12-21 16:28
 */
@Controller
@RequestMapping("rushToPay")
public class RushToPayController {
    @Autowired
    private RushToPayServcie rushToPayServcie;
    @RequestMapping("toPage")
    public String toPage(){
        return "/company/rushToPay";
    }
    @RequestMapping("/page")
    @ResponseBody
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(rushToPayServcie.getList(map));
        Map resultMap = new HashMap();
//        System.out.println("0000000000000000000="+map1);
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody Map map){
        //System.out.println(map+"1111111111111111111111111111111");
       //System.out.println("000000000000"+ rushToPayServcie.update(map));
        return  rushToPayServcie.update(map);
    }
}
