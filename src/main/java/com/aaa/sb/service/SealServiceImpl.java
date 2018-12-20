package com.aaa.sb.service;

import com.aaa.sb.dao.SealDao;
import com.aaa.sb.entity.PersonsAccountNumberState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:SealServiceImpl
 * discription:
 * author:Dbailing
 * createTime:2018-12-19 21:08
 */
@Service
public class SealServiceImpl implements SealService {
    @Autowired
    private SealDao sealDao;
   @Override
    public List<Map> SealedPage(Map map) {
       return sealDao.SealedPage(map);
    }
}
