package com.aaa.sb.controller;

import com.aaa.sb.service.DetailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:DetailController
 * discription:
 * author:zz
 * createTime:2018-12-19 15:19
 */
@Controller
@RequestMapping("detail")
public class DetailController {
    @Autowired
    private DetailService detailService;
    @RequestMapping("toPage")
    public String toPage(){
        return "/company/detail";
    }

    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
//        System.out.println("aaaaaaaaaa"+detailService.getList());
        PageInfo<Map> pageInfo =new PageInfo<Map>(detailService.getList());
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }
}
