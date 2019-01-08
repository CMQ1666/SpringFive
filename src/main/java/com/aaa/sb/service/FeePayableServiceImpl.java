package com.aaa.sb.service;

import com.aaa.sb.dao.FeePayableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:FeePayableServiceImpl
 * discription:
 * author:zz
 * createTime:2018-12-27 21:46
 */
@Service
public class FeePayableServiceImpl implements FeePayableService {
    @Autowired
    private  FeePayableDao feePayableDao;
    @Override
    public List<Map> getList(Map map) {
        return feePayableDao.getList(map);
    }

    @Override
    public Map getSelect(Map map) {
        return feePayableDao.getList1(map);
    }

    @Override
    public Map update(Map map) {
        HashMap hashMap = new HashMap();
        hashMap.put("a",feePayableDao.update(map));
        hashMap.put("b",feePayableDao.update1(map));
        hashMap.put("c",feePayableDao.update2(map));
        List<Map> map1 = feePayableDao.select2(map);
        for (Map map2 : map1) {
            feePayableDao.insert(map2);
        }
        String uaowemonths = map.get("UAOWEMONTHS") + "";
        //遍历插入信息
        List<Map> maps = feePayableDao.select1(map);
        for (Map mapa : maps) {
            mapa.put("uaowemonths",uaowemonths);
            feePayableDao.update3(mapa);
            feePayableDao.insert1(mapa);
            System.out.println((mapa.get("DALANCE")+""));
            System.out.println((mapa.get("PERMONPAYSUM")+""));
            System.out.println(mapa.get(uaowemonths));
        }
        return hashMap;
    }

//    @Override
//    public Map getById(String id) {
//        return feePayableDao.getById(id);
//    }
}
