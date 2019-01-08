package com.aaa.sb.service;

import com.aaa.sb.dao.BaseAdjustmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:BaseAdjustmentServiceImpl
 * discription:
 * author:zz
 * createTime:2018-12-19 21:57
 */
@Service
public class BaseAdjustmentServiceImpl implements BaseAdjustmentService {
    @Autowired
    private BaseAdjustmentDao baseAdjustmentDao;
    @Override
    public List<Map> getList(Map map) {

        return baseAdjustmentDao.getList(map);
    }

    @Override
    public List<Map> getList1(Map map) {
        return baseAdjustmentDao.getList1(map);
    }

    @Override
    public List<Map> getList2(String map) {
        return baseAdjustmentDao.getList2(map);
    }

    @Override
    public Map update(Map map) {
        HashMap hashMap = new HashMap();
        //根据个人账号更改 缴存基数 公司缴纳 个人缴纳
        hashMap.put("a",baseAdjustmentDao.update1(map));
        //根据个人账号更改公司和个人缴纳总金额
        hashMap.put("c",baseAdjustmentDao.update(map));
        //根据公司ID更改应缴纳金额
        hashMap.put("b",baseAdjustmentDao.update2(map));
        return hashMap;
    }
}
