package com.aaa.sb.service.Gjjtq;

import java.util.List;
import java.util.Map;

public interface YdtqService {


    /**
     * 带参分页查询
     * @param map
     * @return
     */
    List<Map> getPageByParam(Map map);



    /**
     * 更新个人信息
     * @param map
     * @return
     */
    int update(Map map);
}
