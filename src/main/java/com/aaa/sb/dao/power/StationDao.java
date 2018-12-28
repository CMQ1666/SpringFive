package com.aaa.sb.dao.power;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:StationDao
 * discription:
 * author:cmq
 * createTime:2018-12-27 20:52
 */

public interface StationDao {

    /**
     * 获得后台用户岗位的数据
     * @param map
     * @return
     */
    @Select(value = "select * from tb_user")
    List<Map> getList(Map map);
}
