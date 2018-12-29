package com.aaa.sb.controller.power;

import com.aaa.sb.entity.Node;
import com.aaa.sb.service.power.PowerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:PowerController
 * discription:
 * author:zz
 * createTime:2018-12-11 10:24
 */
@Controller
@RequestMapping("/power")
public class PowerController {

    //依赖注入
    @Autowired
    private PowerService powerService;

    /**
     * 跳转权限树页面
     * @return
     */
    @RequestMapping("toTree")
    public String toTree(){
        return "power/tree";
    }

    @RequestMapping("toList")
    public String toList(){
        return "power/powerupdate";
    }

    /**
     * 权限树数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/tree")
    public Object tree(){
        return powerService.getList();
    }
    /**
     * 根据角色权限树数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/roleTree")
    public Object roleTree(HttpSession httpSession){
       Map userInfo = (Map)httpSession.getAttribute("userInfo");
        Integer roleid = Integer.valueOf(userInfo.get("ROLEID") + "");
        List<Node> roleList = powerService.getRoleList(roleid);
        return roleList;
    }
    /**
     * 根据角色权限树数据
     * @return
     */
    @ResponseBody
    @RequestMapping("/backTree")
    public Object backTree(@RequestBody Map map){
        int roleid = Integer.valueOf(map.get("roleId")+"");
        List<Node> roleList = powerService.getRoleList(roleid);
        List list = new ArrayList();
        for (Node node : roleList) {
            for (Node child : node.getChildren()) {
                list.add(child.getId());
            }
        }
        return list;
    }

    /**
     * 权限树增加
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public int treeAdd(@RequestBody Map map){
        return powerService.treeAdd(map);
    }
    /**
     * 权限树更新
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public int treeUpdate(@RequestBody Map map){
        return powerService.treeUpdate(map);
    }
    /**
     * 权限树删除
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public int treeDelete(@RequestBody Map map){
        return powerService.treeDetele(map);
    }


    @ResponseBody
    @RequestMapping("/selectTreeByPid")
    public List<Map> getListById(@RequestBody Map map){
        return powerService.getListById(map);
    }
}
