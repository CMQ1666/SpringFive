package com.aaa.sb.service.inspect;

import com.aaa.sb.dao.inspect.AgExtractionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:AgExtractionServiceImpl
 * discription:
 * author:cmq
 * createTime:2018-12-21 11:19
 */
@Service
public class AgExtractionServiceImpl implements AgExtractionService{
    @Autowired
    private AgExtractionDao agExtractionDao;
    @Override
    public List<Map> getList(Map map) {
        return agExtractionDao.getList(map);
    }

    @Override
    public int pass(Map map) {
        return agExtractionDao.pass(map);
    }

    @Override
    public int twoPass(Map map) {
        return agExtractionDao.twoPass(map);
    }
}
