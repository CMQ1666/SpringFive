package com.aaa.sb.controller;

import com.aaa.sb.entity.Dept;
import com.aaa.sb.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className:DeptController
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-11-21 11:26
 */
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 部门列表方法
     * @return
     */
    @RequestMapping("list")
    public Object getList(){
        return deptService.getList();
    }

    /**
     * 部门列表添加方法
     * @param
     * @return
     */
    @RequestMapping("add")
    public Object add(@RequestBody Dept dept){
        int i = deptService.addDept(dept);
        if (i>0){
            return i;
        }
       return 0;
    }

    /**
     * 部门更新
     * @param dept
     * @return
     */
    @RequestMapping("update")
    public Object update(@RequestBody Dept dept){
        int i = deptService.updateDept(dept);
        if (i>0){
            return i;
        }
        return 0;
    }

    /**
     * 删除部门
     * @param deptno
     * @return
     */
    @RequestMapping("del")
    public Object del(Integer deptno){

        int i = deptService.delDept(deptno);
        if (i>0){
            return i;
        }
        return 0;
    }

    /**
     * 根据id获取部门信息
     * @param deptno
     * @return
     */
    @RequestMapping("getone")
    public Object getOne(Integer deptno){
        return deptService.getDeptById(deptno);
    }

}
