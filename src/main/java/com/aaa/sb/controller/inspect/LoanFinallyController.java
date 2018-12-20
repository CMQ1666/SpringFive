package com.aaa.sb.controller.inspect;

import com.aaa.sb.service.inspect.LoanFinallyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:LoanFinallyController
 * discription:
 * author:cmq
 * createTime:2018-12-19 20:18
 */
@Controller
@RequestMapping("loanfinally")
public class LoanFinallyController {
    @Autowired
    private LoanFinallyService loanFinallyService;
    /**
     * 跳转到贷款终审页面
     * @return
     */
    @RequestMapping("/tofinally")
    public String toLoan(){
        return "inspect/loanfinally";
    }

    /**
     * 跳转到信息审查页面
     * @param PID
     * @param model
     * @return
     */
    @RequestMapping("/finallyLittle")
    public String toFinallyLittle(Integer PID, Model model){
        model.addAttribute("PID",PID);
        return "inspect/loanfinallylittle";
    }

    /**
     * 贷款初审页面数据
     * @param map
     * @return
     */
    @RequestMapping("/finally")
    @ResponseBody
    public Object loanFinally(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(loanFinallyService.getList());
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }

    /**
     * 获取信息审查信息
     * @param map
     * @return
     */
    @RequestMapping("/finallytwo")
    @ResponseBody
    public Object loanFinallyLittle(@RequestBody Map map){
        List<Map> maps = loanFinallyService.reList(map);
        return maps;
    }

    /**
     * 获得共同借款人信息
     * @param map
     * @return
     */
    @RequestMapping("/finallythird")
    @ResponseBody
    public Object loanFinallythird(@RequestBody Map map){
        return loanFinallyService.rethList(map);
    }
}
