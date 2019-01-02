package com.aaa.sb.service.Gjjtq;

import java.util.List;
import java.util.Map;

public interface BfxhYdtqService {

    /**
     * 带参分页查询
     * @param map
     * @return
     */
    List<Map> getPageByParam(Map map);


    /**
     * 公积金部分提取与销户提取
     * @param map
     * @return
     */

    int add(Map map);
    /**
     * 偿还购房贷款本息
     */
    int addDaikuan(Map map);

}
