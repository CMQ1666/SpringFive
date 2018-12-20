package com.aaa.sb.service.inspect;

import java.util.List;
import java.util.Map;

/**
 * className:LoanFinallyService
 * discription:
 * author:cmq
 * createTime:2018-12-19 20:15
 */
public interface LoanFinallyService {
    /**
     * 贷款初审数据
     * @return
     */
    List<Map> getList();

    /**
     * 信息审查
     * @param map
     * @return
     */
    List<Map> reList(Map map);

    /**
     * 根据共同借款人姓名查询信息
     * @param map
     * @return
     */
    List<Map> rethList(Map map);
}
