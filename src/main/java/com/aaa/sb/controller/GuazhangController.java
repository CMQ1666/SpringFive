package com.aaa.sb.controller;

import com.aaa.sb.service.GuazhangService;
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
 * className:GuazhangController
 * discription:
 * author:zz
 * createTime:2019-01-11 10:38
 */
@Controller
@RequestMapping("guazhang")
public class GuazhangController {
    @Autowired
    private GuazhangService guazhangService;
    @RequestMapping("toPage")
    public Object toPage(){
        return "/company/guazhangbanli";
    }
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
//        System.out.println("aaaaaaaaaa"+detailService.getList());
        PageInfo<Map> pageInfo =new PageInfo<Map>(guazhangService.getList(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }
   @ResponseBody
   @RequestMapping("update")
    public Object update(@RequestBody Map map){
        return guazhangService.update(map);
    }
}
