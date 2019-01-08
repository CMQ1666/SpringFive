package com.aaa.sb.controller;


import com.aaa.sb.service.PersonService;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.Map;
import com.aaa.sb.entity.Person;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * className:PersonController
 * discription:
 * author:Dbailing
 * createTime:2018-12-12 08:55
 */
@Controller
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;
    /**
     * 跳转人员转移列表页面
     * @return
     */
    @RequestMapping("/toPage")
    public String trans(){ return "/person/PersonTransfer1" ;}

    /**
     *人员转移审核页面
     * @return
     */
    @RequestMapping("/transaudit")
    //public String trans(){ return "/person/PersonTransfer" ;}
    public String transaudit(){ return "/person/persontransfer-audit";}

    /**
     *分页页面
     */
    @ResponseBody
    @RequestMapping("/page")
    public Object page(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(personService.getList(map));
        System.out.println(map.get("pname"));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return  resultMap;

    }

    /**
     * 根据用户姓名查询账户单位和银行
     * @return
     */
    @ResponseBody
    @RequestMapping("/trans/{pname}")
    public Object transfer(@PathVariable("pname") String pname) {
        //System.out.println(pname+"--------------------");
         Map name = personService.UserIDSelect(pname);
       // System.out.println(name+"************************");
        return name;
    }
    /**
     * 获取查询到的下拉框的转为单位的所有名称 然后跳转到页面
     * @return
     */
    @ResponseBody
    @RequestMapping("/select")
    public Object select() {
        List list=new ArrayList(20);//扩容
        return personService.getUnit();
    }

    /**
     * 下拉框选中公司 获取ID  查询公司的受托银行
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/Trustee/{id}")
    public Object Trustee(@PathVariable("id") Integer id) {
        Map uintById = personService.getUintById(id);
        return uintById;
    }
    /**
     * 获取到待审核表 个人转移信信息  和前台获取的值对比 不能有相同操作的人
     * 提交审核的按钮
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/submitVerify")
    public Object submitVerify(@RequestBody Map map){
        List<Map> submitVerify =personService.submitVerify(map);
        if(submitVerify.size()>0){
            return "1";
        }else{
            return "0";
        }
    }
    /**
     *待转移用户列表
     * @return
     */
    @ResponseBody   //标志该方法返回值为json集合对象		相当于response.getWriter().print(json);
    @RequestMapping("/PersonTransfer")
    public Object userList1(@RequestBody Map map) {
        System.out.println("-----------"+map);
        List<Map> userSelect = personService.UserSelect(map);
        List<Person> userList = new ArrayList<Person>();
        if(userSelect!=null&userSelect.size()>0){
            Person person = null;
            String pState = "";
            for (int i = 0; i < userSelect.size(); i++) {
                Map maps = userSelect.get(i);
                if(maps.get("STATE").equals("1")){
                    pState="正常";
                }else if(maps.get("STATE").equals("2")){
                    pState="封存";
                }else{
                    pState="销户";
                }
                person = new Person(maps.get("PNAME")+"", maps.get("IDNUMBER")+"",maps.get("BALANCE")+"",pState);
                userList.add(person);
            }
            //第一个参数是当前第几页页码 第二个参数是显示数量
            PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
            //用pageinfo对结果进行包装
            PageInfo<Map> pageInfo =new PageInfo<Map>(personService.UserSelect(map));
            Map resultMap = new HashMap();
            //获取当前页数据
            resultMap.put("pageData",pageInfo.getList());
            //获取分页总数量
            resultMap.put("total",pageInfo.getTotal()) ;
            return resultMap;

        }else {
            return "0";
        }
    }

    @RequestMapping("/submits")
    public String submits1(@RequestBody Map map,HttpSession session) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String name = session.getAttribute("name")+"";  //获取 当前操作人员
        String  pid = map.get("pid")+"";  //获取id
        Map shift = personService.shift(Integer.valueOf(pid));
        //根据id查询数据 个人信息 然后添加到待转移列表
      //通过id查询的信息   因为前台数据冲突
       Map selName = personService.uname(Integer.valueOf(map.get("selName")+""));
       String puname = selName.get("UNAME")+"";
       //通过id查询的转入单位名称   因为前台数据冲突
        if(shift!=null&&shift.size()>0&&selName!=null&&selName.size()>0){
            Map tempMap = new HashMap();
            tempMap.put("transfer_out_unit", map.get("uname"));//转出单位
            tempMap.put("transfer_into_unit",puname);//转入单位TRANSFER_INTO_UNIT
            tempMap.put("auditor",shift.get("PNAME"));//申请人
            tempMap.put("person_account",shift.get("GRZH"));//个人帐号
            tempMap.put("transfer_reason",map.get("zy"));//转移原因
            tempMap.put("operator",name);//操作人		（操作人待添加）
            tempMap.put("submit_time",df.format(new Date()));//提交时间
            tempMap.put("audit_state","待审核");//审核状态
            tempMap.put("pname",shift.get("PNAME"));//待转移人员姓名
            tempMap.put("id",pid);					//待转移人员id
            tempMap.put("idnumber",shift.get("IDNUMBER"));//证件号码
            tempMap.put("balance",shift.get("DALANCE"));//个人账号余额
            tempMap.put("state",shift.get("PERACCSTATE"));//个人账号状态PERACCSTATE
            tempMap.put("basenummber",Double.parseDouble(shift.get("BASENUMMBER")+""));//个人缴存基数
            if(tempMap!=null&&tempMap.size()>0){
                personService.addShift(tempMap);
            }
        }
        return "redirect:/person/skip1";
    }

    @ResponseBody
    @RequestMapping("/audit")
    public Object audit(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(personService.uditTransfer(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return  resultMap;
    }


}
