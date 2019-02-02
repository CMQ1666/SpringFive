package com.aaa.sb.service.inspect;

import java.util.List;
import java.util.Map;

/**
 * className:PersonTranferAuditService
 * discription:
 * author:cmq
 * createTime:2018-12-26 10:01
 */
public interface PersonTranferAuditService {

    /**
     * 获得人员转移审核数据
     * @param map
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 驳回审核
     * @param map
     * @return
     */
    int updNo(Map map);

    /**
     * 审核通过
     * @return
     */
    int updPass(Map map);
}
