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
            "<where> AUDIT_STATE='待审核' <if test=\"AUDITOR!=null and AUDITOR!=''\"> and AUDITOR like '%'||#{AUDITOR}||'%'</if></where></script>")
    List<Map> getList(Map map);

    /**
     * 驳回审核
     * @param map
     * @return
     */
    @Update(value = "update tb_transfer_audit set AUDIT_STATE='驳回' where PERSON_ACCOUNT=#{PERSON_ACCOUNT}")
    int updNo(Map map);

    /**
     * 审核通过
      * @return
     */
    @Update(value = "update tb_transfer_audit set AUDIT_STATE='已通过' where PERSON_ACCOUNT=#{PERSON_ACCOUNT}")
    int updPassOne(Map map);

    /**
     * 通过时修改个人账户表
     * @param map
     * @return
     */
    @Update(value = "update tb_paccountutil set UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT})," +
            "UBITNROP=(select UDEPOSITRATIO from TB_UNITACCOUNT where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT}))," +
            "INDINROP=(select UPERSONRATIO from TB_UNITACCOUNT where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT}))," +
            "UNITMONPAYSUM=(select BASENUMMBER from tb_paccountutil where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT}))*(select UDEPOSITRATIO from TB_UNITACCOUNT where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT}))/100," +
            "PERMONPAYSUM=(select BASENUMMBER from tb_paccountutil where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT}))*(select UPERSONRATIO from TB_UNITACCOUNT where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT}))/100," +
            "YDRAWAMT=(select BASENUMMBER from tb_paccountutil where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT}))*(select UDEPOSITRATIO from TB_UNITACCOUNT where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT}))/100+(select BASENUMMBER from tb_paccountutil where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT}))*(select UPERSONRATIO from TB_UNITACCOUNT where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT}))/100 " +
            " where GRZH=#{PERSON_ACCOUNT}")
    int updPassTwo(Map map);

    /**
     * 通过时修改原公司账户表
     * @param map
     * @return
     */
    @Update(value = "update TB_UNITACCOUNT set UDEPOSITEDPNUM=UDEPOSITEDPNUM-1," +
            "UAOWEMONERY=UAOWEMONERY-(select BASENUMMBER from tb_paccountutil where GRZH=#{PERSON_ACCOUNT})*(select UDEPOSITRATIO from TB_UNITACCOUNT where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_OUT_UNIT}))/100 " +
            "where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_OUT_UNIT})")
    int updPassThree(Map map);

    /**
     * 通过时修改新公司账户表
     * @param map
     * @return
     */
    @Update(value = "update TB_UNITACCOUNT set UDEPOSITEDPNUM=UDEPOSITEDPNUM-1," +
            "UAOWEMONERY=UAOWEMONERY-(select BASENUMMBER from tb_paccountutil where GRZH=#{PERSON_ACCOUNT})*(select UDEPOSITRATIO from TB_UNITACCOUNT where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT}))/100 " +
            "where UNID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT})")
    int updPassFour(Map map);

    /**
     * 通过时修改个人信息表所属公司id
     * @param map
     * @return
     */
    @Update(value = "update TB_PERSON_INFO set UNIT_ID=(select UNID from tb_unit where UNAME=#{TRANSFER_INTO_UNIT})")
    int updPassFive(Map map);
}
