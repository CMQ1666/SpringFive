package com.aaa.sb.controller.inspect;

import com.aaa.sb.service.inspect.SealAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * className:SealAuditController
 * discription:
 * author:cmq
 * createTime:2018-12-25 21:13
 */
@Controller
@RequestMapping("/sealAt")
public class SealAuditController {
    @Autowired
    private SealAuditService sealAuditService;

    /**
     * 封存审核通过
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updPass")
    public int updPass(@RequestBody Map map){
        int i = sealAuditService.updAudit(map);
        int j = sealAuditService.updPaccount(map);
        int k = sealAuditService.updUaccount(map);
        int z = i+j+k;
        return z;
    }

    /**
     * 启封审核通过
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updOpen")
    public int updOpen(@RequestBody Map map){
        int i = sealAuditService.updOpenAudit(map);
        int j = sealAuditService.updOpenPaccoutn(map);
        int k = sealAuditService.updOpenUaccount(map);
        int z = i+j+k;
        return z;
    }

    /**
     * 驳回申请
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updNo")
    public int updNo(@RequestBody Map map){
        return sealAuditService.updNo(map);
    }
}
