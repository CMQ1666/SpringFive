package com.aaa.sb.service;

import com.aaa.sb.dao.DealBillDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:DealBillServiceImpl
 * discription:
 * author:zz
 * createTime:2018-12-26 14:07
 */
@Service
public class DealBillServiceImpl implements DealBillService {
    @Autowired
    private DealBillDao dealBillDao;
    @Override
    public List<Map> getList(Map map) {

        return dealBillDao.getList(map);
    }

    @Override
    public int update(Map map) {
        return dealBillDao.update(map);
    }
}
