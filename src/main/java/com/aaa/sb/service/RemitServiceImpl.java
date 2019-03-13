package com.aaa.sb.service;

import com.aaa.sb.dao.RemitDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:RemitServiceImpl
 * discription:
 * author:zz
 * createTime:2018-12-18 16:30
 */
@Service
public class RemitServiceImpl  implements  RemitService{
    @Autowired
    private RemitDao remitDao;
    @Override
    public List<Map> getList(Map map ) {
        return remitDao.getList(map);
    }

    @Override
    public List<Map> getList1(Map map) {
        return remitDao.getList1(map);
    }

    @Override
    public List<Map> getList2(String map) {
        return remitDao.getList2(map);
    }

    @Override
    public Object add(Map map) {
        //remitDao.add1(map);
        remitDao.update(map);
        remitDao.update1(map);
        return remitDao.add1(map);
    }

//    @Override
//    public Object update(Map map) {
//        Map map1=new HashMap();
//        int i=remitDao.update(map);
//        int i1=remitDao.update1(map);
//        map1.put("i",i);
//        map1.put("i1",i1);
//        return map1;
//    }
}
