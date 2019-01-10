package com.aaa.sb.controller;

import com.aaa.sb.service.DealBillService;
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
 * className:DealBillController
 * discription:
 * author:zz
 * createTime:2018-12-26 14:10
 */
@Controller
@RequestMapping("dealBill")
public class DealBillController {
    @Autowired
    private DealBillService dealBillService;
    @RequestMapping("toPage")
    public Object toPage(){
        return "/company/dealBill";
    }
    @RequestMapping("/page")
    @ResponseBody
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(dealBillService.getList(map));
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
        return dealBillService.update(map);
    }
}
