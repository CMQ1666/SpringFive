package com.aaa.sb.service;

import com.aaa.sb.dao.ExamineDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:ExamineServiceImpl
 * discription:
 * author:Dbailing
 * createTime:2018-12-22 20:14
 */
@Service

public class ExamineServiceImpl implements ExamineService {
    @Autowired
  private   ExamineDao examineDao;

    @Override
    public List<Map> getList() {
        return examineDao.getList();
    }

    @Override
    public List<Map> transfer(Map map) {
        return examineDao.transfer(map);
    }

    @Override
    public List<Map> extract(Map map) {
        return examineDao.extract(map);
    }

    @Override
    public List<Map> loans(Map map) {
        return examineDao.loans(map);
    }

    @Override
    public List<Map> breaka(Map map) {
        return examineDao.breaka(map);
    }
}
