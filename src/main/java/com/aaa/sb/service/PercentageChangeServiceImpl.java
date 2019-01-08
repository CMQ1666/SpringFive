package com.aaa.sb.service;

import com.aaa.sb.dao.PercentageChangeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:PercentageChageServiceImpl
 * discription:
 * author:zz
 * createTime:2018-12-19 22:38
 */
@Service
public class PercentageChangeServiceImpl implements PercentageChangeService {
    @Autowired
    private PercentageChangeDao percentageChangeDao;
    @Override
    public List<Map> getList(Map map) {
        return percentageChangeDao.getList(map);
    }

    @Override
    public List<Map> getList1(Map map) {
        return percentageChangeDao.getList1(map);
    }

    @Override
    public List<Map> getList2(String map) {
        return percentageChangeDao.getList2(map);
    }

    @Override
    public Map update(Map map) {
        HashMap hashMap = new HashMap();
        //根据个人账号更改 缴存基数 公司缴纳 个人缴纳
        hashMap.put("a",percentageChangeDao.update1(map));
        //根据个人账号更改公司和个人缴纳总金额
        hashMap.put("c",percentageChangeDao.update(map));
        //根据公司ID更改应缴纳金额
        hashMap.put("b",percentageChangeDao.update2(map));
        hashMap.put("d",percentageChangeDao.update3(map));
        return hashMap;

    }
}
