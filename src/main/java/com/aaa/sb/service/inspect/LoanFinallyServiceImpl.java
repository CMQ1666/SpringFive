package com.aaa.sb.service.inspect;

import com.aaa.sb.dao.inspect.LoanFinallyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:LoanFinallyServiceImpl
 * discription:
 * author:cmq
 * createTime:2018-12-19 20:16
 */
@Service
public class LoanFinallyServiceImpl implements LoanFinallyService{
    @Autowired
    private LoanFinallyDao loanFinallyDao;
    @Override
    public List<Map> getList(Map map) {
        return loanFinallyDao.getList(map);
    }

    @Override
    public List<Map> reList(Map map) {
        return loanFinallyDao.reList(map);
    }

    @Override
    public List<Map> rethList(Map map) {
        return loanFinallyDao.rethList(map);
    }

    @Override
    public int thirdUpdate(Map map) {
        int i = loanFinallyDao.fiveUpdate(map);
        return loanFinallyDao.thirdUpdate(map);
    }

    @Override
    public int fourUpdate(Map map) {
        int i = loanFinallyDao.sixUpdate(map);
        return loanFinallyDao.fourUpdate(map);
    }
}
