package com.aaa.sb.service.Gjjtq;

import java.util.List;
import java.util.Map;

public interface TqhdService {


    /**
     * 带参分页查询
     * @param map
     * @return
     */
    List<Map> getPageByParam(Map map);

    /**
     * 分页总数量
     * @param map
     * @return
     */
    int getPageCount(Map map);

    /**
     * 添加还贷人员
     * @param map
     * @return
     */
    int add(Map map);

    /**
     * 查看还贷记录
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 删除还贷人员
     * @param empNo
     * @return
     */
    int delete(int empNo);

    /**
     * 批量删除还贷人员
     * @param _s
     * @return
     */
    int batchDel(String _s);

    /**
     * 查询提取还贷人员信息
     * @param map
     * @return
     */
    List<Map> getTqhd(Map map);
}
