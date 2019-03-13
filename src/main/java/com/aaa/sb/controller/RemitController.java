package com.aaa.sb.controller;

import com.aaa.sb.service.RemitService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * className:RemitController
 * discription:
 * author:zz
 * createTime:2018-12-18 16:34
 */
@Controller
@RequestMapping("/remit")
public class RemitController {
    @Autowired
    private RemitService remitService;
    @RequestMapping("/toPage")
    public String toPage(){
        return "/company/remit";
    }
    @ResponseBody
    @RequestMapping("/add")
    public Object add(@RequestBody Map map){
        //System.out.println(map+"..........");
        return remitService.add(map);
    }
    @RequestMapping("/page")
    @ResponseBody
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(remitService.getList(map));
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
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(remitService.getList1(map));
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

        return remitService.getList2(map);
    }
//    @ResponseBody
//    @RequestMapping("update")
//    public Object update(@RequestBody Map map){
//        return remitService.update(map);
//    }
}
