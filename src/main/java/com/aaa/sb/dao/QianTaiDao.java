package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:QianTaiDao
 * discription:
 * author:qcm
 * createTime:2018-12-26 20:27
 */
public interface QianTaiDao {
    /**
     * 个人账号登录前台
     * @param map
     * @return
     */
    @Select("select a.*,b.* from tb_paccountutil a,tb_person_info b where a.pid=b.pid and GRZH=#{GRZH} and GRMM=#{GRMM}")
    List<Map> ChackPersonLogin(Map map);

    /**
     * 查询缴存记录
     * @param map
     * @return
     */
    @Select("select GRZH,OPENDATE,DALANCE,YINTERESTBAL,PERMONPAYSUM from tb_paccountutil where GRZH=#{GRZH}")
    List<Map> SelectCheckJiLu(Map map);

    /**
     * 查询个人贷款
     * @param map
     * @return
     */
    @Select("select GRZH,PNAME,LOAN_MONEY,LOAN_PERIODS,CTIME,REPAY_MONEY from tb_repay_detail where GRZH=#{GRZH}")
    List<Map> SelectCheckDaiKuanJiLu(Map map);
    /**
     * 个人还款
     */

    @Select("select * from tb_repay where GRZH=#{GRZH}")
    List<Map> selecthuankuan(Map map);
    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    @Select("select a.*,b.* from tb_unitaccount a,tb_unit b where a.unid=b.unid and DWZH=#{DWXZ} and ULegalCard=#{ULegalCard}")
    List<Map> ChackUnitLogin(Map map);
    /**
     * 单位账号登录前台
     * @param map
     * @return
     */
    @Select("select a.*,b.* from tb_unitaccount a,tb_unit b where a.unid=b.unid and DWZH=#{DWXZ}")
    List<Map> ChackUnitLogin1(Map map);

    /**
     * 单位账号缴纳记录
     * @param map
     * @return
     */
    @Select("select a.*,b.*,c.* from tb_unit a join tb_unitaccount b join tb_unitbizdetail c on a.uid=b.uid and b.uaID=c.uaId and t.DWZH=#{DWZH}")
    List<Map> UnitJiaoNaJiLu(Map map);

    /**
     * 查询政策法规
     * @param map
     * @return
     */
    @Select("<script>select * from tb_news \n" +
            "<where>ZNAME='已发布' and TNAME='政策法规'</where></script>")
    List<Map> getPageByParam(Map map);

    /**
     * 查询工作动态
     * @param map
     * @return
     */
    @Select("<script>select * from tb_news \n" +
            "<where>ZNAME='已发布' and TNAME='工作动态'</where></script>")
    List<Map> getPageByTNAME(Map map);
}
