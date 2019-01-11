package com.aaa.sb.service;

import com.aaa.sb.dao.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * className:CompanyServiceImpl
 * discription:
 * author:zz
 * createTime:2018-12-17 10:05
 */
@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;
    @Override
    public Map add(Map map) {
        //System.out.println(map+"..............");
            int add = companyDao.add(map);
            int i = companyDao.add1(map);
            Map paramMap =new HashMap();
            paramMap.put("add",add);
            paramMap.put("i",i);
            return paramMap;
        }

    @Override
    public int uname(String name) {
        return companyDao.uname(name);
    }
}

