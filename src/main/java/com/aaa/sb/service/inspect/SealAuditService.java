package com.aaa.sb.service.inspect;

import java.util.Map;

/**
 * className:SealAuditService
 * discription:
 * author:cmq
 * createTime:2018-12-25 20:59
 */
public interface SealAuditService {
    /**
     * 通过封存申请
     * @param map
     * @return
     */
    int updAudit(Map map);

    /**
     * 封存修改个人账户
     * @param map
     * @return
     */
    int updPaccount(Map map);

    /**
     * 封存修改公司账户
     * @param map
     * @return
     */
    int updUaccount(Map map);

    /**
     * 通过启封申请
     * @param map
     * @return
     */
    int updOpenAudit(Map map);

    /**
     * 启封修改个人账户
     * @param map
     * @return
     */
    int updOpenPaccoutn(Map map);

    /**
     * 启封修改公司账户
     * @param map
     * @return
     */
    int updOpenUaccount(Map map);

    /**
     * 审核驳回申请
     * @param map
     * @return
     */
    int updNo(Map map);
}
