package com.aaa.sb.controller.inspect;

import com.aaa.sb.service.inspect.AgExtractionService;
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
 * className:AgExtractionController
 * discription:
 * author:cmq
 * createTime:2018-12-21 11:28
 */
@Controller
@RequestMapping("/agExtraction")
public class AgExtractionController {
    //依赖注入
    @Autowired
    private AgExtractionService agExtractionService;

    /**
     * 跳转到约定提取页面
     * @return
     */
    @RequestMapping("/toAgEx")
    public String toAgEx(){
        return "inspect/agreedextraction";
    }

    /**
     * 查询待审核约定提取数据
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/agExList")
    public Object getList(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(agExtractionService.getList(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }

    /**
     * 通过审核
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/pass")
    public int pass(@RequestBody Map map){
        int i=agExtractionService.pass(map);
        return i;
    }

    /**
     * 驳回审核
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/twoPass")
    public int twoPass(@RequestBody Map map){
        int i=agExtractionService.twoPass(map);
        return i;
    }

}
