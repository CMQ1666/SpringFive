package com.aaa.sb.service;

import com.aaa.sb.dao.RushToPayDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:RushToPayServiceImpl
 * discription:
 * author:zz
 * createTime:2018-12-21 16:24
 */
@Service
public class RushToPayServiceImpl implements RushToPayServcie {
    @Autowired
    private RushToPayDao rushToPayDao;
    @Override
    public List<Map> getList(Map map) {
        return rushToPayDao.getList(map);
    }

    @Override
    public int update(Map map) {
        return rushToPayDao.update(map);
    }
    
}
