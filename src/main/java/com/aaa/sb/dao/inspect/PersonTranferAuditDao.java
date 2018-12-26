package com.aaa.sb.dao.inspect;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:PersonTranferAuditDao
 * discription:
 * author:cmq
 * createTime:2018-12-26 09:59
 */
public interface PersonTranferAuditDao {

    /**
     * 获得人员转移审核数据
     * @param map
     * @return
     */
    @Select("<script> select TRANSFER_ID,AUDITOR,TRANSFER_OUT_UNIT,TRANSFER_INTO_UNIT,PERSON_ACCOUNT,TRANSFER_REASON,OPERATOR,SUBMIT_TIME,AUDIT_STATE from tb_transfer_audit \n" +
            "<where> <if test=\"AUDITOR!=null and AUDITOR!=''\"> and AUDITOR like '%'||#{AUDITOR}||'%'</if></where></script>")
    List<Map> getList(Map map);

    /**
     * 驳回审核
     * @param map
     * @return
     */
    @Update(value = "update tb_transfer_audit set AUDIT_STATE='驳回' where PERSON_ACCOUNT=#{PERSON_ACCOUNT}")
    int updNo(Map map);


}
