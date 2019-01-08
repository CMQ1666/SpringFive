package com.aaa.sb.controller;

import com.aaa.sb.service.PercentageChangeService;
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
 * className:PercentageChangeController
 * discription:
 * author:zz
 * createTime:2018-12-19 22:40
 */
@Controller
@RequestMapping("percentage")
public class PercentageChangeController {
    @Autowired
    private PercentageChangeService percentageChageService;
    @RequestMapping("toPage")
    public String toPage(){
        return "/company/percentageChange";
    }
    @RequestMapping("/page")
    @ResponseBody
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(percentageChageService.getList(map));
        Map resultMap = new HashMap();
//        System.out.println("0000000000000000000="+map1);
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }
    @RequestMapping("/page1")
    @ResponseBody
    public Object page1(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(percentageChageService.getList1(map));
        Map resultMap = new HashMap();
//        System.out.println("0000000000000000000="+map1);
        //获取当前页数据
        resultMap.put("pageData1",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total1",pageInfo.getTotal()) ;
        return resultMap;
    }
    @ResponseBody
    @RequestMapping("/getPersonInfo/{grzh}")
    public Object getPersonInfo(@PathVariable("grzh") String map){
        //System.out.println("222222222222222222");
        //System.out.println(percentageChageService.getList2(map));
        return percentageChageService.getList2(map);
    }
    @ResponseBody
    @RequestMapping("update")
    public Object update(@RequestBody Map map){
        return percentageChageService.update(map);
    }
}
