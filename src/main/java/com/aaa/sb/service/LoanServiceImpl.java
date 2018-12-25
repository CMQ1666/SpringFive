package com.aaa.sb.service;

import com.aaa.sb.dao.LoanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:LoanServiceImpl
 * discription:
 * author:MVP
 * createTime:2018-12-10 14:58
 */
@SuppressWarnings("all")
@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanDao loanDao;

    @Override
    public int addLoan(List<Map> map) {
        int i= 0;
        Map map2=new HashMap();
        for (Map map1:map){
            map2.putAll(map1);
        }
      //  System.out.println(map2+"**************");
        i = loanDao.addLoan(map2);
        return i ;
    }

    @Override
    public int addRepay(List<Map> map) {
        int i= 0;
        Map map2=new HashMap();
        for (Map map1:map){
            map2.putAll(map1);
        }
        //  System.out.println(map2+"**************");
        i = loanDao.addRepay(map2);
        return i ;

    }

    @Override
    public Map getList(String GRZH) {
        return loanDao.getList(GRZH);
    }
}
