package com.aaa.sb.service;

import com.aaa.sb.dao.DeptDao;
import com.aaa.sb.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @className:DeptServiceImpl
 * @discription:
 * @author:ProMonkey-K
 * @creatTime:2018-11-21 11:25
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;
    @Override
    public List<Dept> getList() {
        return deptDao.getList();
    }

    @Override
    public int addDept(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public int updateDept(Dept dept) {
        return deptDao.updateDept(dept);
    }

    @Override
    public int delDept(Integer deptno) {
        return deptDao.delDept(deptno);
    }

    @Override
    public Dept getDeptById(Integer deptno) {
        return deptDao.getDeptById(deptno);
    }
}
