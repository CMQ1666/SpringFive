package com.aaa.sb.controller;
import com.aaa.sb.service.CompanyService;
import com.aaa.sb.util.FtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * className:CompanyController
 * discription:
 * author:zz
 * createTime:2018-12-17 10:13
 */
@Controller
@RequestMapping("company")
public class CompanyController {
     @Autowired
    private CompanyService companyService;
    @Autowired
    private FtpUtil ftpUtil;

    @ResponseBody
     @RequestMapping("add")
     public Object add(@RequestBody Map map)
    {
        //System.out.println(map+"0000000000000000000000000000000000000000000000000000000000");
        return companyService.add(map);
    }
     @RequestMapping("toPage")
    public String toPage(){
         return "/company/companyOpen";
     }
    /**
     * 公司名称唯一性验证
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping("/uname/{UNAME}")
    public Object name(@PathVariable("UNAME") String name){
        return companyService.uname(name);
    }
    /**
     * 上传方法
     * @param file
     * @return
     */
  @ResponseBody
  @RequestMapping("/upLoadPic")
   public Object upLoadPic(@RequestParam MultipartFile file){
       //System.out.println(file);
       String s = ftpUtil.upLoad(file);
       return s;
   }
}
