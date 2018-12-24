package com.aaa.sb.service.inspect;

import java.util.List;
import java.util.Map;

/**
 * className:LoanFirstService
 * discription:
 * author:cmq
 * createTime:2018-12-17 10:01
 */
public interface LoanFirstService {
    /**
     * 贷款初审数据
     * @return
     */
    List<Map> getList(Map map);

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

    /**
     * 初审通过
     * @return
     */
    int firstUpdate(Map map);

    /**
     * 初审驳回
     * @return
     */
    int twoUpdate(Map map);
}
