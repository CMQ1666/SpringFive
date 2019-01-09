package com.aaa.sb.service.Qtgl;

import java.util.List;
import java.util.Map;

public interface ZcxgFbService {
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    List<Map> getPageByParam(Map map);

    /**
     * 添加新闻
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 更新新闻
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 删除新闻
     * @param empNo
     * @return
     */
    int delete(int empNo);

    /**
     * 批量删除新闻
     * @param _s
     * @return
     */
    int batchDel(String _s);
}
