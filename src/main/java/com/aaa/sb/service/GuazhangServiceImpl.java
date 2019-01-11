package com.aaa.sb.service;

import com.aaa.sb.dao.GuazhangDao;
import org.apache.ibatis.type.DoubleTypeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:guazhangServiceImpl
 * discription:
 * author:zz
 * createTime:2019-01-11 10:33
 */
@Service
public class GuazhangServiceImpl implements GuazhangService {
    @Autowired
    private GuazhangDao guazhangDao;
    @Override
    public List<Map> getList(Map map) {
        return guazhangDao.getList(map);
    }

    @Override
    public int update(Map map) {
        //System.out.println(map+"0000000000000000000000000000000");
        return guazhangDao.update(map);
    }
}
