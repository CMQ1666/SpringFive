package com.aaa.sb.controller.Zhxx;

import com.aaa.sb.service.Zhxx.DwxxService;
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
@RequestMapping("/Dwxx")
public class DwxxController {
    @Autowired
    private DwxxService dwxxService;
    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toDwxx")
    public String toList(){

        return "Zhxx/Dwxx";
    }

    /**
     * 分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageDwxx")
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("start") + ""), Integer.valueOf(map.get("end") + ""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo = new PageInfo<Map>(dwxxService.getPageByParam(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData", pageInfo.getList());
        //获取分页总数量
        resultMap.put("total", pageInfo.getTotal());
        return resultMap;
    }


    /**
     * 修改单位信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updDwxx")
    public Object update(@RequestBody Map map){
        return dwxxService.update(map);
    }
}
