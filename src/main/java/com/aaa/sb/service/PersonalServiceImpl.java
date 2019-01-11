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
        List<Map> unidByParam = personalDao.getUnidByParam(map);
        if(unidByParam!=null&&unidByParam.size()>0){
            map.put("UNID",unidByParam.get(0).get("UNID"));
        }
        //Map map2=personalDao.getUnitInfo(map);
        //int i11=personalDao.add1(map2);
        //map.put("UAID",map2.get("UAID"));
        int i=personalDao.add(map);
        //#{BASENUMMBER}*#{UBITNROP}*0.01
        Integer BASENUMMBER=Integer.valueOf(map.get("BASENUMMBER")+"");
        Integer UDEPOSITRATIO=Integer.valueOf(map.get("UDEPOSITRATIO")+"");
        Integer UPERSONRATIO=Integer.valueOf(map.get("UPERSONRATIO")+"");
        System.out.println(BASENUMMBER+"0000000000000"+UDEPOSITRATIO);
        map.put("BASENUMMBER",BASENUMMBER);
        map.put("UBITNROP",UDEPOSITRATIO);
        map.put("UPERSONRATIO",UPERSONRATIO);
        //map.put("UNITMONPAYSUM",BASENUMMBER*UBITNROP*0.01);
        int i1=personalDao.add1(map);
        int i2=personalDao.update(map);
        int i3=personalDao.update1(map);
        Map map1 =new HashMap();
        map1.put("i",i);
        map1.put("i1",i1);
        map1.put("i2",i2);
        map1.put("i3",i3);
        return map1;
    }

    @Override
    public Map getList(Map map) {

        return personalDao.getUnitInfo(map);
    }

}
