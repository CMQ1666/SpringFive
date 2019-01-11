package com.aaa.sb.controller;

import com.aaa.sb.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.Map;

/**
 * className:PersonalController
 * discription:
 * author:zz
 * createTime:2018-12-18 11:12
 */
@Controller
@RequestMapping("personal")
public class PersonalController {
    @Autowired
    private PersonalService personalService;

    @RequestMapping("toPage")
    public String toPage() {
        return "/company/personOpen";
    }

    @ResponseBody
    @RequestMapping("add")
    public Object add(@RequestBody Map map) {

        //System.out.println("map0000000000000000000000000000000"+map);
        //personalService.add(map);
        return personalService.add(map);
    }
@ResponseBody
@RequestMapping("getList")
    public Map getList(@RequestBody Map map) {

        return personalService.getList(map);
    }


}