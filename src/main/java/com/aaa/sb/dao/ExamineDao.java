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
    /**
     * 查询人员转移记录表
     */
    @Select("select * from tb_transfer_audit")
    List<Map> transfer(Map map);
    /**
     * 查询封存、启封、销户 记录表
     */
    @Select("select * from tb_unseal_audit")
    List<Map> breaka(Map map);
    /**
     * 查询公积金提取记录表
     */
    @Select("select pre_account,appl_name,comp_name,application_type,application_way,extract_reason,extract_money,appl_time,appl_state from tab_extract_application")
    List<Map> extract(Map map);
    /**
     * 查询贷款记录表中的信息 录入查看审批表中
     * @return
     */
    @Select("select a.*,b.pid,c.idNUmber from tb_loanappval a join tb_paccountutil b on a.pid=b.pid join tb_person_info c on b.pid=c.pid\n")
    List<Map> loans(Map map);

}
