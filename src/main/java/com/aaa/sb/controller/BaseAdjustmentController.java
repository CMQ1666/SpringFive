package com.aaa.sb.controller;

import com.aaa.sb.service.BaseAdjustmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:BaseAdjustmentController
 * discription:
 * author:zz
 * createTime:2018-12-19 22:00
 */
@Controller
@RequestMapping("base")
public class BaseAdjustmentController {
    @Autowired
    private BaseAdjustmentService baseAdjustmentService;
    @RequestMapping("toPage")
    public String toPage(){
        return "/company/baseAdjustment";
    }
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
//        System.out.println("aaaaaaaaaa"+detailService.getList());
        PageInfo<Map> pageInfo =new PageInfo<Map>(baseAdjustmentService.getList(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }
    @ResponseBody
    @RequestMapping("/page1")
    public Object page1(@RequestBody Map map){
        System.out.println(map+"00000000000000000000000000");
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
//        System.out.println("aaaaaaaaaa"+detailService.getList());
        PageInfo<Map> pageInfo =new PageInfo<Map>(baseAdjustmentService.getList1(map));
        System.out.println(baseAdjustmentService.getList1(map)+"000000000000000000000000000000000000000000000000000000");
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData1",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total1",pageInfo.getTotal()) ;
        return resultMap;
    }
    @ResponseBody
    @RequestMapping("/getPersonInfo/{grzh}")
    public Object getPersonInfo(@PathVariable("grzh") String map){
        System.out.println("222222222222222222");
        System.out.println(baseAdjustmentService.getList2(map));
        return baseAdjustmentService.getList2(map);
    }
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody Map map){
        return baseAdjustmentService.update(map);
    }
}
