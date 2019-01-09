package com.aaa.sb.service.Zhxx;

import java.util.List;
import java.util.Map;

public interface DwxxService {

    /**
     * 分页查询
     * @param
     * @return
     */
    List<Map> getPageByParam(Map map);





    /**
     * 更新单位信息
     * @param map
     * @return
     */
    int update(Map map);
}
