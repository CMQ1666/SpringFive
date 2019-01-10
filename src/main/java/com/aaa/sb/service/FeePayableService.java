package com.aaa.sb.service;

import java.util.Map;
import java.util.List;

public interface FeePayableService {
    List<Map> getList(Map map);
    /**
     * 根据公司账号查询数据
     * @param map
     * @return
     */
    Map getSelect(Map map);
    /**
     * 更新
     * @param map
     * @return
     */
    Map update(Map map);

//    /**
//     * 查询监听键盘事件所需要的数据
//     * @param id
//     * @return
//     */
//    Map getById(String id);
}
