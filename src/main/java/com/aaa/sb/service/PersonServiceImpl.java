package com.aaa.sb.service;

import com.aaa.sb.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:PersonServiceImpl
 * discription:
 * author:Dbailing
 * createTime:2018-12-13 14:50
 */
@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    private PersonDao personDao;

    @Override
    public List<Map> submitVerify(Map map) {
        return personDao.submitVerify(map);
    }

    @Override
    public Map uname(Integer unid) {
        return personDao.uname(unid);
    }

    @Override
    public int addShift(Map map) {
        return personDao.addShift(map);
    }

    @Override
    public Map shift(Integer pid) {
        return personDao.shift(pid);
    }

    @Override
    public List<Map> getList(Map map) {
        return personDao.getList(map);
    }

    @Override
    public Map UserIDSelect(String pname) {
        return personDao.UserIDSelect(pname);
    }

    @Override
    public List<Map> getUnit() {
        return personDao.getUnit();
    }

    @Override
    public Map getUintById(Integer unid) {
        return personDao.getUintById(unid);
    }

    @Override
    public List<Map> uditTransfer(Map map) {
        return personDao.uditTransfer(map);
    }

    @Override
    public List<Map> UserSelect(Map map) {
        return personDao.UserSelect(map);
    }
}
