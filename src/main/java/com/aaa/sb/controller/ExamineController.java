package com.aaa.sb.controller;

import com.aaa.sb.service.ExamineService;
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
 * className:ExamineController
 * discription:
 * author:Dbailing
 * createTime:2018-12-22 20:15
 */
@Controller
@RequestMapping("/exam")
public class ExamineController {
    @Autowired
    private ExamineService examineService;
    /**
     * 跳转查看审批列表页面
     * @return
     */
    @RequestMapping("/toPage")
    public String trans(){ return "/person/ExamList" ;}

    /**
     *查询审批分页页面
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(examineService.getList());
        System.out.println(map.get("pname"));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return  resultMap;

    }

}
