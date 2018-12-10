package com.aaa.sb.dao;

import com.aaa.sb.entity.Dept;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className:DeptDao
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-11-13 09:39
 */
public interface DeptDao {
    /**
     * 部门列表查询
     * @return
     */
    List<Dept> getList();

    /**
     * 部门添加
     * @param dept
     * @return
     */
    int addDept(Dept dept);

    /**
     * 部门更新
     * @param dept
     * @return
     */
    int updateDept(Dept dept);

    /**
     * 部门删除
     * @param deptno
     * @return
     */
    int delDept(Integer deptno);

    /**
     * 根据id查询部门信息
     * @param deptno
     * @return
     */
    Dept getDeptById(Integer deptno);

}
