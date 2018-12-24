package com.aaa.sb.controller.inspect;

import com.aaa.sb.service.inspect.LoanFirstService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:loanfirst
 * discription:
 * author:cmq
 * createTime:2018-12-17 08:45
 */
@Controller
@RequestMapping("/loanfirst")
public class LoanFirstController {

    @Autowired
    private LoanFirstService loanFirstService;

    /**
     * 跳转到贷款初审页面
     * @return
     */
    @RequestMapping("/toLoan")
    public String toLoan(){
        return "inspect/loanfirst";
    }

    /**
     * 跳转到信息审查页面
     * @param PID
     * @param model
     * @return
     */
    @RequestMapping("/toLittle")
    public String toLittle(Integer PID, Model model){
        model.addAttribute("PID",PID);
        return "inspect/loanfirstlittle";
    }

    /**
     * 贷款初审页面数据
     * @param map
     * @return
     */
    @RequestMapping("/first")
    @ResponseBody
    public Object loanFirst(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(loanFirstService.getList(map));
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
    @RequestMapping("/second")
    @ResponseBody
    public Object loanFirstLittle(@RequestBody Map map){
        List<Map> maps = loanFirstService.reList(map);
        return maps;
    }

    /**
     * 获得共同借款人信息
     * @param map
     * @return
     */
    @RequestMapping("/third")
    @ResponseBody
    public Object loanthird(@RequestBody Map map){
        return loanFirstService.rethList(map);
    }

    /**
     * 初审通过
     * @return
     */
    @RequestMapping("/update")
    public Object firstUpdate(@RequestParam Map map){
        loanFirstService.firstUpdate(map);
        return "inspect/loanfirst";
    }

    /**
     * 初审失败
     * @return
     */
    @RequestMapping("/updatet")
    public Object twoUpdate(@RequestParam Map map){
        loanFirstService.twoUpdate(map);
        return "inspect/loanfirst";
    }
}
