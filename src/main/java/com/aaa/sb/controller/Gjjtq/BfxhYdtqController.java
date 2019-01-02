package com.aaa.sb.controller.Gjjtq;

import com.aaa.sb.service.Gjjtq.BfxhYdtqService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/BfxhYdtq")
public class BfxhYdtqController {
    @Autowired
    private BfxhYdtqService bfxhYdtqService;

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toBfxhYdtq")
    public String toList(){

        return "Gjjtq/BfxhYdtq";
    }

    /**
     * 分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageBfxhYdtq")
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("start") + ""), Integer.valueOf(map.get("end") + ""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo = new PageInfo<Map>(bfxhYdtqService.getPageByParam(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData", pageInfo.getList());
        //获取分页总数量
        resultMap.put("total", pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 添加
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/addBfxhYdtq")
    public Object add(@RequestBody Map map){
        return bfxhYdtqService.add(map);
    }

}
