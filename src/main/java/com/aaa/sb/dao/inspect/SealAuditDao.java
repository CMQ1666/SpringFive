package com.aaa.sb.dao.inspect;

import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * className:SealAuditDao
 * discription:
 * author:cmq
 * createTime:2018-12-25 20:29
 */

public interface SealAuditDao {
    /**
     * 通过封存申请
     * @param map
     * @return
     */
    @Update(value = "update tb_unseal_audit set AUDIT_NAME=2 where UNSEAL_ACCOUNT=#{UNSEAL_ACCOUNT}")
    int updAudit(Map map);

    /**
     * 封存修改个人账户
     * @param map
     * @return
     */
    @Update(value = "update tb_paccountutil set PERACCSTATE='封存' where GRZH=#{UNSEAL_ACCOUNT}")
    int updPaccount(Map map);

    /**
     * 封存修改公司账户
     * @param map
     * @return
     */
    @Update(value = "update tb_unitaccount set UDEPOSITEDPNUM=UDEPOSITEDPNUM-1,UAOWEMONERY=UAOWEMONERY-(select UNITMONPAYSUM from tb_paccountutil where GRZH =#{UNSEAL_ACCOUNT}) where UNID =(select UNID from tb_paccountutil where GRZH = #{UNSEAL_ACCOUNT})")
    int updUaccount(Map map);

    /**
     * 通过启封申请
     * @param map
     * @return
     */
    @Update(value = "update tb_unseal_audit set AUDIT_NAME=2 where UNSEAL_ACCOUNT=#{UNSEAL_ACCOUNT}")
    int updOpenAudit(Map map);

    /**
     * 启封修改个人账户
     * @param map
     * @return
     */
    @Update(value = "update tb_paccountutil set PERACCSTATE='正常' where GRZH=#{UNSEAL_ACCOUNT}")
    int updOpenPaccoutn(Map map);

    /**
     * 启封修改公司账户
     * @param map
     * @return
     */
    @Update(value = "update tb_unitaccount set UDEPOSITEDPNUM=UDEPOSITEDPNUM+1,UAOWEMONERY=UAOWEMONERY+(select UNITMONPAYSUM from tb_paccountutil where GRZH =#{UNSEAL_ACCOUNT}) where UNID =(select UNID from tb_paccountutil where GRZH = #{UNSEAL_ACCOUNT})")
    int updOpenUaccount(Map map);

    /**
     * 审核驳回申请
     * @param map
     * @return
     */
    @Update(value = "update tb_unseal_audit set AUDIT_NAME=0 where UNSEAL_ACCOUNT=#{UNSEAL_ACCOUNT}")
    int updNo(Map map);
}
