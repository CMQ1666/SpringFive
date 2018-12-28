package com.aaa.sb.controller.inspect;

import com.aaa.sb.service.inspect.PersonTranferAuditService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * className:PersonTranferAuditController
 * discription:
 * author:cmq
 * createTime:2018-12-26 10:08
 */
@Controller
@RequestMapping("/tranAudit")
public class PersonTranferAuditController {
    @Autowired
    private PersonTranferAuditService personTranferAuditService;

    /**
     * 跳转到人员转移页面
     * @return
     */
    @RequestMapping("toTranAudit")
    public String toTranAudit(){
        return "inspect/persontranferaudit";
    }

    /**
     * 获得人员转移审核数据
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public Object getList(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(personTranferAuditService.getList(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }

    /**
     * 驳回审核
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/unPass")
    public Object updNo(@RequestBody Map map){
        return personTranferAuditService.updNo(map);
    }

    /**
     * 通过审核
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/pass")
    public Object updPass(@RequestBody Map map){
        return personTranferAuditService.updPass(map);
    }
}
