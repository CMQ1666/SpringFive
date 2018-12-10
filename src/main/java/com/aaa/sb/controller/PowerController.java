package com.aaa.sb.controller;

import com.aaa.sb.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:
 * author:Dbailing
 * createTime:2018-12-08 19:15
 */
@Controller
@RequestMapping("/power")
public class PowerController {
   @Autowired
   private PowerService  powerService;


    /**
     * 权限数json数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/tree")
    public Object powerTree(){
        return powerService.getListt();
    }
    }

