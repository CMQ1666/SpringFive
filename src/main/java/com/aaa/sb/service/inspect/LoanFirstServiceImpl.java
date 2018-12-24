package com.aaa.sb.service.inspect;

import com.aaa.sb.dao.inspect.LoanFirstDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:LoanFirstServiceImpl
 * discription:
 * author:cmq
 * createTime:2018-12-17 09:53
 */
@Service
public class LoanFirstServiceImpl implements LoanFirstService{
    @Autowired
    private LoanFirstDao loanFirstDao;


    @Override
    public List<Map> getList(Map map) {
        return loanFirstDao.getList(map);
    }

    @Override
    public List<Map> reList(Map map) {
        return loanFirstDao.reList(map);
    }

    @Override
    public List<Map> rethList(Map map) {
        return loanFirstDao.rethList(map);
    }

    @Override
    public int firstUpdate(Map map) {
        return loanFirstDao.firstUpdate(map);
    }

    @Override
    public int twoUpdate(Map map) {
        return loanFirstDao.twoUpdate(map);
    }
}
