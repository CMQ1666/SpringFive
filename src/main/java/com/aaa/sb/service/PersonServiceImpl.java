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
    public List<Map> getList() {
        return personDao.getList();
    }
}
