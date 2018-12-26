package com.aaa.sb.controller;

import com.aaa.sb.service.FinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;

/**
 * className:FinalcalController
 * discription:
 * author:Dbailing
 * createTime:2018-12-24 09:58
 */
@Controller
@RequestMapping("/money")
public class FinalcalController {
    @Autowired
    private FinalService fund;
    /**
     * 跳转到财务统计图页面
     *
     */
    @RequestMapping("/list")
    public String list(){ return "/financial/HJQKFX" ;}

    /**
     *人员转移审核页面
     * @return
     */
    @RequestMapping("/page")
    //public String trans(){ return "/person/PersonTransfer" ;}
    public String page(){ return "/financial/DKQKFX";}
    /**
     * 每月贷款信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/DaiKuanXinxi")
    public Object DaiKuanXinxi() {
        List<Map> list = fund.DaiKuanXinxi();
        return list;

    }

    /**
     * 每月还款信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/huanKuanXinxi")
    public Object huanKuanXinxi() {
        List<Map> list = fund.huanKuanXinxi();
        return list;

    }

    /**
     * 统计汇缴每月金额
     * @return
     */
    @ResponseBody
    @RequestMapping("/meiYueJinE")
    public Object meiYueJinE() {

        List<Map> list = fund.meiYueJinE();
        return list;

    }

    /**
     * 统计每月提取金额
     * @return
     */
    @ResponseBody
    @RequestMapping("/meiYueTiqu")
    public Object meiYueTiqu() {
        List<Map> list = fund.meiYueTiqu();
        return list;
    }


}
