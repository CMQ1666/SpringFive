package com.aaa.sb.service.inspect;

import com.aaa.sb.dao.inspect.PersonTranferAuditDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:PersonTranferAuditServiceImpl
 * discription:
 * author:cmq
 * createTime:2018-12-26 10:07
 */
@Service
public class PersonTranferAuditServiceImpl implements PersonTranferAuditService{
    @Autowired
    private PersonTranferAuditDao personTranferAuditDao;
    @Override
    public List<Map> getList(Map map) {
        return personTranferAuditDao.getList(map);
    }

    @Override
    public int updNo(Map map) {
        return personTranferAuditDao.updNo(map);
    }
}
