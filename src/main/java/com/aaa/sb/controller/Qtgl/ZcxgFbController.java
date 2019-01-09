package com.aaa.sb.controller.Qtgl;


import com.aaa.sb.service.Qtgl.ZcxgFbService;
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
@RequestMapping("ZcxgFb")
public class ZcxgFbController {

    @Autowired
    private ZcxgFbService zcxgFbService;

    /**
     * 跳转列表页面
     * @return
     */
    @RequestMapping("/toZcxgFb")
    public String toList(){

        return "Qtgl/ZcxgFb";
    }

    /**
     * 分页
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageZcxgFb")
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("start") + ""), Integer.valueOf(map.get("end") + ""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo = new PageInfo<Map>(zcxgFbService.getPageByParam(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData", pageInfo.getList());
        //获取分页总数量
        resultMap.put("total", pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 添加新闻信息
     * @param map
     * @return
     * RequestBody 该方法接受的数据为json对象
     * ResponseBody 该方法返回值为json对象
     */
    @ResponseBody
    @RequestMapping("/addZcxgFb")
    public Object add(@RequestBody Map map){

        return zcxgFbService.add(map);
    }

    /**
     * 修改新闻信息
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updZcxgFb")
    public Object update(@RequestBody Map map){

        return zcxgFbService.update(map);
    }
    /**
     * 删除新闻信息
     * @param NEWSID
     * @return
     */
    @ResponseBody
    @RequestMapping("/delZcxgFb")
    public Object delete(Integer NEWSID){

        return zcxgFbService.delete(NEWSID);
    }

    /**
     * 批量删除新闻
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchDelZcxgFb")
    public Object batchDel(@RequestBody  Map map){
        return zcxgFbService.batchDel(map.get("_s")+"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ");
    }
}
