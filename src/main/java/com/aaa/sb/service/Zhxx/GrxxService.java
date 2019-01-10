package com.aaa.sb.service.Zhxx;

import java.util.List;
import java.util.Map;

public interface GrxxService {

    /**
     * 分页查询
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
