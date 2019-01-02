package com.aaa.sb.service.inspect;

import com.aaa.sb.dao.inspect.ExtractionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:ExtractionServiceImpl
 * discription:
 * author:cmq
 * createTime:2018-12-24 10:53
 */
@Service
public class ExtractionServiceImpl implements ExtractionService{
    @Autowired
    private ExtractionDao extractionDao;

    @Override
    public List<Map> getList(Map map) {
        return extractionDao.getList(map);
    }

    @Override
    public List<Map> getCount(Map map) {
        return extractionDao.getCount(map);
    }

    @Override
    public int updPass(Map map) {

        return  extractionDao.updPass(map);
    }

    @Override
    public int updMinus(Map map) {
        return extractionDao.updMinus(map);
    }

    @Override
    public int updLose(Map map) {
        return extractionDao.updLose(map);
    }

    @Override
    public int udpDelete(Map map) {
        return extractionDao.udpDelete(map);
    }

    @Override
    public int updateUnitPeople(Map map) {
        return extractionDao.updateUnitPeople(map);
    }

    @Override
    public int updateUaOweMonery(Map map) {
        return extractionDao.updateUaOweMonery(map);
    }

    @Override
    public int udpShXh(Map map) {
        return extractionDao.udpShXh(map);
    }
}
