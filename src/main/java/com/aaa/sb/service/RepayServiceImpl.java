package com.aaa.sb.service;

import com.aaa.sb.dao.RepayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:RepayServiceImpl
 * discription:
 * author:MVP
 * createTime:2018-12-15 16:15
 */
@Service
public class RepayServiceImpl implements RepayService{
        @Autowired
        private RepayDao repayDao;


    @Override
    public List<Map> getList(Map map) {
        return repayDao.getList(map);
    }
}
