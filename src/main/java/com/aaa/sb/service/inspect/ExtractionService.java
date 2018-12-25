package com.aaa.sb.service.inspect;

import java.util.List;
import java.util.Map;

/**
 * className:ExtractionService
 * discription:
 * author:cmq
 * createTime:2018-12-24 10:53
 */
public interface ExtractionService {

    /**
     * 获取提取数据
     * @param map
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 根据个人账号查提取申请表信息
     * @param map
     * @return
     */
    List<Map> getCount(Map map);

    /**
     * 根据个人账号通过申请
     * @param map
     * @return
     */
    int updPass(Map map);

    /**
     * 提取余额
     * @param map
     * @return
     */
    int updMinus(Map map);

    /**
     * 驳回申请
     * @param map
     * @return
     */
    int updLose(Map map);

    /**
     * 销户
     * @return
     */
    int udpDelete(Map map);

    /**
     * 销户成功
     * @param map
     * @return
     */
    int udpShXh(Map map);

}
