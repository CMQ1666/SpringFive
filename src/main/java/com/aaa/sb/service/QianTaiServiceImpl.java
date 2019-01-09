package com.aaa.sb.service;

import com.aaa.sb.dao.QianTaiDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:QianTaiServiceImpl
 * discription:
 * author:qcm
 * createTime:2018-12-26 20:28
 */
@Service
public class QianTaiServiceImpl implements QianTaiService{
    @Autowired
    private QianTaiDao qianTaiDao;

    /**
     * 个人账号登录前台
     * @param map
     * @return
     */
    @Override
    public List<Map> ChackPersonLogin(Map map) {
        return qianTaiDao.ChackPersonLogin(map);
    }

    /**
     * 查询缴存记录
     * @param map
     * @return
     */
    @Override
    public List<Map> SelectCheckJiLu(Map map) {
        return qianTaiDao.SelectCheckJiLu(map);
    }

    /**
     * 查询缴存记录
     * @param map
     * @return
     */
    @Override
    public List<Map> SelectCheckDaiKuanJiLu(Map map) {
        return qianTaiDao.SelectCheckDaiKuanJiLu(map);
    }

    /**
     * 个人还款
     * @param map
     * @return
     */
    @Override
    public List<Map> selecthuankuan(Map map) {
        return qianTaiDao.selecthuankuan(map);
    }

    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    @Override
    public List<Map> ChackUnitLogin(Map map) {
        return qianTaiDao.ChackUnitLogin(map);
    }

    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    @Override
    public List<Map> ChackUnitLogin1(Map map) {
        return qianTaiDao.ChackUnitLogin1(map);
    }

    /**
     * 单位账号缴纳记录
     * @param map
     * @return
     */
    @Override
    public List<Map> UnitJiaoNaJiLu(Map map) {
        return qianTaiDao.UnitJiaoNaJiLu(map);
    }

    /**
     * 查询政策法规
     * @param map
     * @return
     */
    @Override
    public List<Map> getPageByParam(Map map) {
        return qianTaiDao.getPageByParam(map);
    }

    /**
     * 查询工作动态
     * @param map
     * @return
     */
    @Override
    public List<Map> getPageByTNAME(Map map) {
        return qianTaiDao.getPageByTNAME(map);
    }
}
