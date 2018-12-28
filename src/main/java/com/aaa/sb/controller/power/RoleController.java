package com.aaa.sb.controller.power;

import com.aaa.sb.service.power.RoleService;
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
 * className:RoleController
 * discription:
 * author:cmq
 * createTime:2018-12-27 19:02
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 跳转到角色管理页面
     * @return
     */
    @RequestMapping("toRole")
    public String toRole(){
        return "role/role";
    }

    @ResponseBody
    @RequestMapping("/roleList")
    public Object getList(@RequestBody Map map){
        //第一个参数是当前第几页页码 第二个参数是显示数量
        PageHelper.startPage(Integer.valueOf(map.get("pageNo")+""),Integer.valueOf(map.get("pageSize")+""));
        //用pageinfo对结果进行包装
        PageInfo<Map> pageInfo =new PageInfo<Map>(roleService.getList(map));
        Map resultMap = new HashMap();
        //获取当前页数据
        resultMap.put("pageData",pageInfo.getList());
        //获取分页总数量
        resultMap.put("total",pageInfo.getTotal()) ;
        return resultMap;
    }

    /**
     * 获得所有角色
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/getRoleList")
    public Object getRoleList(){
        return roleService.getRoleList();
    }

    /**
     * 添加角色
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("insertRole")
    public Object insertRole(@RequestBody Map map){
        return roleService.inRole(map);
    }

    /**
     * 更新角色
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateRole")
    public Object updateRole(@RequestBody Map map){
        return roleService.updRole(map);
    }

    /**
     * 删除角色
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteRole")
    public Object deleteRole(@RequestBody Map map){
        return roleService.deleteRole(map);
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchDel")
    public Object deleteManyRole(String ids){
        return roleService.deleteManyRole(ids);
    }
}
