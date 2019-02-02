package com.aaa.sb.service.inspect;

import com.aaa.sb.dao.inspect.SealAuditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * className:SealAuditServiceImpl
 * discription:
 * author:cmq
 * createTime:2018-12-25 21:11
 */
@Service
public class SealAuditServiceImpl implements SealAuditService{
    @Autowired
    private SealAuditDao sealAuditDao;
    @Override
    public int updAudit(Map map) {
        return sealAuditDao.updAudit(map);
    }

    @Override
    public int updPaccount(Map map) {
        return sealAuditDao.updPaccount(map);
    }

    @Override
    public int updUaccount(Map map) {
        return sealAuditDao.updUaccount(map);
    }

    @Override
    public int updOpenAudit(Map map) {
        return sealAuditDao.updOpenAudit(map);
    }

    @Override
    public int updOpenPaccoutn(Map map) {
        return sealAuditDao.updOpenPaccoutn(map);
    }

    @Override
    public int updOpenUaccount(Map map) {
        return sealAuditDao.updOpenUaccount(map);
    }

    @Override
    public int updNo(Map map) {
        return sealAuditDao.updNo(map);
    }
}
