package com.aaa.sb.controller;

import com.aaa.sb.service.SealService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.SourceTree;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
 * className:SealController
 * discription:
 * author:Dbailing
 * createTime:2018-12-20 08:19
 */
@Controller
@RequestMapping("/seal")
public class SealController {
    @Autowired
    private SealService sealService;

    /**
     *
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object list(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(sealService.SealedPage(map));

        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return  resultMap;
    }
    /**
     * 跳转人员转移列表页面
     * @return
     */
    @RequestMapping("/toPage")
    public String toPage(){ return "/person/PersonList" ;}
    /**
     * 跳转人员转移列表页面
     * @return
     */
    @RequestMapping("/toAudit")
    public String toAudit(){ return "/person/Seal-audit" ;}
    /**
     * 封存 启封 销户  操作弹出层查询信息
     * @param map
     * @return
     * 根据个人账号信息来查询
     * element
     * 放到service层
     */
    @ResponseBody
    @RequestMapping("/query")
    public Object operationQuery(@RequestBody Map map){//RequestBody
        System.out.println("前台"+map);
        List<Map> verification = sealService.verification(map);  //查询到  封存 启封 销户 审核表信息
        List<Map> loansVerification = sealService.loansVerification(map);//校验贷款的人 不能封存和销户  查询贷款人员信息
        Map operationMap = sealService.operationQuery(map);  //查询到个人信息 用于封存 启封 销户   操作弹出层查询信息
        Map maps = new HashMap();
        if(loansVerification!=null&&loansVerification.size()>0){ //判断按照GRZH查询贷款人 如有贷款人 进去 把这条信息传到前台 前台进行判断
            maps.put("daikan","0");
        }else{
            if(verification!=null&&verification.size()>0){ //判断按照GRZH查询封存 销户 启封 是否重复操作 查询是否有这条信息 在判断
                maps.put("grzh","0");
            }else{
                return operationMap;
            }
        }

        return maps;
    }
    /**
     * 封存 启封 销户 每个按钮修改的信息
     * @param map
     * @param session
     * @return
     * element controller放到service层
     */
    @ResponseBody
    @RequestMapping("/save")
    public Object Archive1(@RequestBody Map map, HttpSession session){
        Map unsealAudit = sealService.unsealAudit(map,session);  //把查询的信息和页面信息  传带服务层  然后放入临时的map  把map添加到启封审核表中
        int unsealAuditAdd = sealService.unsealAuditAdd(unsealAudit);	//获取审核信息 添加到审核表中
        return unsealAuditAdd;
    }

    /**
     * 启封 封存审核表分页查询
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/audit")
    public Object sealAudit(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(sealService.sealAudit(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return  resultMap;
    }



}
