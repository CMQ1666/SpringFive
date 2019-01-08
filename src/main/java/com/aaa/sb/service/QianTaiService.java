package com.aaa.sb.service;

import java.util.List;
import java.util.Map;

/**
 * className:QianTaiService
 * discription:
 * author:qcm
 * createTime:2018-12-26 20:28
 */
public interface QianTaiService {
    /**
     * 个人账号登录前台
     * @param map
     * @return
     */
    List<Map> ChackPersonLogin(Map map);

    /**
     * 查询缴存记录
     * @param map
     * @return
     */
    List<Map> SelectCheckJiLu(Map map);

    /**
     * 查询缴存记录
     * @param map
     * @return
     */
    List<Map> SelectCheckDaiKuanJiLu(Map map);
    /**
     * 个人还贷
     */

    List<Map> selecthuankuan(Map map);
    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    List<Map> ChackUnitLogin(Map map);

    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    List<Map> ChackUnitLogin1(Map map);

    /**
     * 单位账号缴纳记录
     * @param map
     * @return
     */
    List<Map> UnitJiaoNaJiLu(Map map);

    /**
     * 查询政策法规
     * @param map
     * @return
     */
    List<Map> getPageByParam(Map map);

    /**
     * 查询工作动态
     * @param map
     * @return
     */
    List<Map> getPageByTNAME(Map map);
}
