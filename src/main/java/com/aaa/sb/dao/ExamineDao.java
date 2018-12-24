package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:ExamineDao
 * discription:
 * author:Dbailing
 * createTime:2018-12-22 20:14
 */

public interface ExamineDao {
    /**
     * 审批工作类别查询
     * @return
     */
    @Select("select * from tb_accraditation")
    List<Map> getList();
}
