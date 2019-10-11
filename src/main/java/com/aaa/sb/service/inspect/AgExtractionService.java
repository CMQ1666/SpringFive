package com.aaa.sb.service.inspect;

import java.util.List;
import java.util.Map;

/**
 * className:AgExtractionService
 * discription:
 * author:cmq
 * createTime:2018-12-21 11:18
 */
public interface AgExtractionService {
    /**
     * 约定提取页面
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 通过审核
     * @param map
     * @return
     */
    int pass(Map map);
    /**
     * 驳回审核
     * @param map
     * @return
     */
    int twoPass(Map map);
}
