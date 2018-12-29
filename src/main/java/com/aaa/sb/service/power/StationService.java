package com.aaa.sb.service.power;

import java.util.List;
import java.util.Map;

/**
 * className:StationService
 * discription:
 * author:cmq
 * createTime:2018-12-27 20:56
 */
public interface StationService {
    /**
     * 获得后台用户岗位的数据
     * @param map
     * @return
     */
    List<Map> getList(Map map);

    /**
     * 添加岗位
     * @param map
     * @return
     */
    int addStation(Map map);

    /**
     * 更新用户表
     * @param map
     * @return
     */

    int updateStation(Map map);

    /**
     * 删除用户表
     * @param map
     * @return
     */
    int deleteStation(Map map);
}
