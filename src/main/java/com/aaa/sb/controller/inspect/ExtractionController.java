package com.aaa.sb.controller.inspect;

import com.aaa.sb.service.inspect.ExtractionService;
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
 * className:ExtractionController
 * discription:
 * author:cmq
 * createTime:2018-12-24 10:59
 */

@Controller
@RequestMapping("/extraction")
public class ExtractionController {

    @Autowired
    private ExtractionService extractionService;

    @RequestMapping("/toExtraction")
    public String toExtraction(){
        return "inspect/extraction";
    }

    @ResponseBody
    @RequestMapping("/exList")
    public Object exList(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(extractionService.getList(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }

    /**
     * 根据个人账户获得 提取详细信息
     * @param map
     * @return
     */
    @RequestMapping("/exCount")
    public Object exCount(Map map){
        return extractionService.getCount(map);
    }

    /**
     * 完成提取通过
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/exUpdPass")
    public int updPass(@RequestBody Map map){
        int i = extractionService.updPass(map);
        int j = extractionService.updMinus(map);
        int o = i+j;
        return o;
    }

    /**
     * 驳回申请
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/exUpdLose")
    public int updLose(@RequestBody Map map){
        return extractionService.updLose(map);
    }

    @ResponseBody
    @RequestMapping("/exDelete")
    public int updDelete(@RequestBody Map map){
        int i = extractionService.udpDelete(map);
        int j = extractionService.udpShXh(map);
        int z=i+j;
        return z;
    }
}
