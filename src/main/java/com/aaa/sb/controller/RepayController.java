package com.aaa.sb.controller;

import com.aaa.sb.service.RepayService;
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
 * className:RepayController
 * discription:
 * author:MVP
 * createTime:2018-12-15 16:18
 */
@Controller
@RequestMapping("repay")
public class RepayController {
    @Autowired
    private RepayService repayService;

    /**
     * 跳转贷款人员列表
     * @return
     */
    @RequestMapping("/toRepay")
    public String trans (){
        return  "/repay/repay";
    }

    /**
     * 列表分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(repayService.getList(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;

        return  resultMap;

    }
}
