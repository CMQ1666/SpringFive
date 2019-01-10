package com.aaa.sb.service;

import java.util.List;
import java.util.Map;

/**
 * className:RepayService
 * discription:
 * author:MVP
 * createTime:2018-12-15 16:14
 */
public interface RepayService {
    /**
     * 查询贷款数据
     * @param map
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 查询个人贷款信息
     * @param
     * @return
     */
    List<Map>getListByName(String GRZH);

    /**
     * 还款计算
     * @param map
     * @return
     */
    int archiveRepay(Map map);

    /**
     * 查询
     */
    int archiveRepay1(Map map);
    /**
     * 查询个人还款记录
     * @param GRZH
     * @return
     */
    List<Map> getRecordByName(String GRZH);

    /**
     * 插入个人还款记录
     * @param map
     * @return
     */
    int insertRecord(Map map);

    /**
     * 提前还款
     * @param map
     * @return
     */
    List<Map> tiqian(Map map);

    /**
     * 逾期还款
     * @param map
     * @return
     */
    List<Map> yuqi(Map map);

    /**
     * 逾期利息计算
     * @param GRZH
     * @return
     */


    List<Map> yuqiList(String GRZH);






}
