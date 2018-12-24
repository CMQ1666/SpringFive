package com.aaa.sb.service;

import com.aaa.sb.dao.FinalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:FinalServiceImpl
 * discription:
 * author:Dbailing
 * createTime:2018-12-24 11:46
 */
@Service
public class FinalServiceImpl implements FinalService {
    @Autowired
    private FinalDao finalDao;
    @Override
    public List<Map> DaiKuanXinxi() {
        return finalDao.DaiKuanXinxi();
    }

    @Override
    public List<Map> meiYueJinE() {
        return finalDao.meiYueJinE();
    }

    @Override
    public List<Map> meiYueTiqu() {
        return finalDao.meiYueTiqu();
    }

    @Override
    public List<Map> huanKuanXinxi() {
        return finalDao.huanKuanXinxi();
    }
}
