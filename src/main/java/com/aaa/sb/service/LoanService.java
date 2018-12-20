package com.aaa.sb.service;

import java.util.List;
import java.util.Map;

/**
 * className:LoanService
 * discription:
 * author:MVP
 * createTime:2018-12-10 14:56
 */
public interface LoanService {
    /**
     *
     * 获取贷款客户列表
     * @param
     * @return
     */
    Map getList(String GRZH);

    /**
     * 添加贷款信息
     * @param map
     * @return
     */

    int addLoan( List<Map> map);

}
