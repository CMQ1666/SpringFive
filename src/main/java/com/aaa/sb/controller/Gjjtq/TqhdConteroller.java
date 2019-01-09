package com.aaa.sb.controller.Gjjtq;

import com.aaa.sb.service.Gjjtq.TqhdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/Tqhd")
public class TqhdConteroller {

    @Autowired
    private TqhdService tqhdService;

    /**
     * 跳转列表页面
     *
     * @return
     */
    @RequestMapping("/toTqhd")
    public String toList() {

        return "Gjjtq/Tqhd";
    }

    /**
     * 分页
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageTqhd")
    public Object page(@RequestBody Map map) {
        Map resultMap = new HashMap();
        resultMap.put("pageData", tqhdService.getPageByParam(map));
        resultMap.put("total", tqhdService.getPageCount(map));
        return resultMap;
    }

    /**
     * 添加还贷人员
     *
     * @param map
     * @return RequestBody 该方法接受的数据为json对象
     * ResponseBody 该方法返回值为json对象
     */
    @ResponseBody
    @RequestMapping("/addTqhd")
    public Object add(@RequestBody Map map) {
        return tqhdService.add(map);
    }

    /**
     * 查看还贷记录
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updTqhd")
    public Object update(@RequestBody Map map) {
        return tqhdService.update(map);
    }

    /**
     * 删除还贷人员
     *
     * @param empNo
     * @return
     */
    @ResponseBody
    @RequestMapping("/delTqhd")
    public Object delete(Integer empNo) {

        return tqhdService.delete(empNo);
    }

    /**
     * 批量删除还贷人员
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchDelTqhd")
    public Object batchDel(@RequestBody Map map) {
        return tqhdService.batchDel(map.get("_s") + "                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                ");
    }

    /**
     * 查询提取还贷人员信息
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/toHdry")
    public Object repayLoan(@RequestBody Map map) {
        Map resultMap = new HashMap();
        resultMap.put("pageData", tqhdService.getTqhd(map));
        return resultMap;

    }
}