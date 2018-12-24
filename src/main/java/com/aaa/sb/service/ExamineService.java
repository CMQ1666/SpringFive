package com.aaa.sb.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:ExamineService
 * discription:
 * author:Dbailing
 * createTime:2018-12-22 20:14
 */

public interface ExamineService {
    /**
     * 审批工作类别查询
     * @return
     */
    @Select("select * from accraditation")
    List<Map> getList();
}
