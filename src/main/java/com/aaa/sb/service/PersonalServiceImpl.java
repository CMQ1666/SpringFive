package com.aaa.sb.service;

import com.aaa.sb.dao.PersonalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:PersonalServiceImpl
 * discription:
 * author:zz
 * createTime:2018-12-18 11:00
 */
@Service
public class PersonalServiceImpl implements PersonalService {
    @Autowired
    private PersonalDao personalDao;
    @Override
    public Map add(Map map) {
        int i=personalDao.add(map);
        List<Map> unidByParam = personalDao.getUnidByParam(map);
        if(unidByParam!=null&&unidByParam.size()>0){
            map.put("UNID",unidByParam.get(0).get("UNID"));
        }
        int i1=personalDao.add1(map);
        Map map1 =new HashMap();
        map1.put("i",i);
        map1.put("i1",i1);
        return map1;
    }

    @Override
    public Map getList(Map map) {

        return personalDao.getUnitInfo(map);
    }

}
