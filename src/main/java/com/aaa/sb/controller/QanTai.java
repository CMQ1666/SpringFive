package com.aaa.sb.controller;

import com.aaa.sb.service.QianTaiService;


import com.aaa.sb.util.PhoneTest;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:QanTai
 * discription:
 * author:qcm
 * createTime:2018-12-26 20:26
 */
@Controller
@RequestMapping("/qiantai")
public class QanTai {

    @Autowired
    private QianTaiService qianTaiService;

    @Autowired
    private HttpSession httpSession;

    /**
     * 前台个人登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/denglu")
    public Object tree(@RequestBody  Map map) {
        List<Map> list = qianTaiService.ChackPersonLogin(map);
        httpSession.setAttribute("list", list);
        if (list.size() > 0 && list != null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 缴存记录
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/seljiao")
    public Object SelJiao(@RequestParam Map map){
        List<Map> list = qianTaiService.SelectCheckJiLu(map);
        return list;
    }

    /**
     * 贷款信息记录
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/daikuan")
    public Object DaiKuan(@RequestParam Map map){
        List<Map> list = qianTaiService.SelectCheckDaiKuanJiLu(map);
        return list;
    }
    /**
     * 前台单位登录
     * @return
     */
    @RequestMapping("/danwei")
    public Object DanWei(@RequestParam Map map){
        List<Map> list = qianTaiService.ChackUnitLogin(map);
        httpSession.setAttribute("list",list);
        if (list.size()>0&&list!=null){
            return "XxGjj/login/dxinxi";
        }
        return "XxGjj/login/danwei";
    }
    /**
     * 还款
     */
    @ResponseBody
    @RequestMapping("/huankuan")
    public Object huankuan(@RequestParam Map map){
        List<Map> list = qianTaiService.selecthuankuan(map);
        return list;
    }
    /**
     * 前台单位登录基金缴纳
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/danjiaona")
    public Object DanJiaoNa(@RequestParam Map map){
        List<Map> list = qianTaiService.ChackUnitLogin1(map);
        return list;
    }
    /**
     * 前台单位登录单位缴纳记录
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/seldanjiao")
    public Object DanJiaoNaJiLu(@RequestParam Map map){
        List<Map> list = qianTaiService.UnitJiaoNaJiLu(map);
        return list;
    }

    /**
     * 政策法规新闻
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageQianTai")
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("start") + ""), Integer.valueOf(map.get("end") + ""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo = new PageInfo<Map>(qianTaiService.getPageByParam(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData", pageInfo.getList());
        //获取分页总数量
        resultMap.put("total", pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 工作动态新闻
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/pageTNAME")
    public Object pageTNAME(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("start") + ""), Integer.valueOf(map.get("end") + ""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo = new PageInfo<Map>(qianTaiService.getPageByTNAME(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData", pageInfo.getList());
        //获取分页总数量
        resultMap.put("total", pageInfo.getTotal());
        return resultMap;
    }

    /**
     * 短信验证码验证
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("sendCode")
    public int sendCode(@RequestBody Map map) {
        System.out.println("前台传来的值===："+map.get("tel"));
        int modelMsg =PhoneTest.getModelMsg(map.get("tel")+"");
        return  modelMsg ;
    }
}
