package com.aaa.sb.controller.Zhxx;

import com.aaa.sb.service.Zhxx.GrxxService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Grxx")
public class GrxxController {

    @Autowired
    private GrxxService grxxService;

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toGrxx")
    public String toList(){

        return "Zhxx/Grxx";
    }

    /**
     * 分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageGrxx")
    public Object page(@RequestBody Map map) {
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("start") + ""), Integer.valueOf(map.get("end") + ""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo = new PageInfo<Map>(grxxService.getPageByParam(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData", pageInfo.getList());
        //获取分页总数量
        resultMap.put("total", pageInfo.getTotal());

        return resultMap;
    }


    /**
     * 修改个人信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updGrxx")
    public Object update(@RequestBody Map map){
        return grxxService.update(map);
    }

}
