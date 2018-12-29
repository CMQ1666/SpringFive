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
    /**
     * 查询人员转移记录表
     */
    List<Map> transfer(Map map);

    /**
     * 查询公积金提取记录表
     */
    List<Map> extract(Map map);

    /**
     * 查询封存、启封、销户 记录表
     */
    List<Map> breaka(Map map);
    /**
     * 查询贷款记录表中的信息 录入查看审批表中
     * @return
     */
    List<Map> loans(Map map);


}
