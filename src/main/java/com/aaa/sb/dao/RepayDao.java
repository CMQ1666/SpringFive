package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:RepayDao
 * discription:
 * author:MVP
 * createTime:2018-12-15 11:05
 */
public interface RepayDao {
    /**
     * 总贷款信息
     * @param map
     * @return
     */

    /*@Select("<script>select r.REPAYED_ALL_MONEY,l.grzh,a.pname,\n" +
                "l.loan_money,l.loan_periods,l.loan_rate,\n" +
                "r.repayed_money,r.residue_periods,r.residue_money\n" +
                "from tb_loanappval l left join  tb_paccountutil p  on l.grzh=p.grzh\n" +
                "left join tb_person_info a on a.pid=p.pid  left join tb_repay r on r.grzh=l.grzh" +*/
        /*@Select("<script> select a.pname,a.grzh,a.loan_money,loan_periods,repayed_money,residue_periods,residue_money from tb_repay a  \n" +


               "<where><if test=\"pname!=null and pname!=''\"> and pname like '%'||#{pname}||'%'</if></where></script>")*/
             /*select grzh,pname,loan_money,loan_rate,loan_periods,repayed_periods," +
                " RESIDUE_PERIODS ,REPAYED_PERIODS from tb_repay */
        @Select("<script> select * from tb_repay "+
                " <where><if test=\"PNAME!=null and PNAME!=''\"> and a. PNAME like '%'||#{PNAME}||'%'</if></where></script>")
    List<Map> getList(Map map);

    /**
     * 查询个人的贷款信息
     * @param
     * @return
     */
    @Select("<script>select r.REPAYED_ALL_MONEY,r.GRZH,r.ALL_MONEY,r.PNAME,r.LOAN_MONEY,r.LOAN_PERIODS,r.LOAN_RATE,r.RESIDUE_ALL_MONEY,\n" +
            "            r.REPAYED_PERIODS,r.RESIDUE_PERIODS,r.RESIDUE_MONEY ,r.RESIDUE_INTERESTS,r.REPAY_MONTH,r.REPAY_MONEY,REPAY_INTERESTS from \n" +
            "            tb_repay r left join tb_loanappval l on l.pid=r.pid left join tb_person_info a on a.pid=l.pid  where r.grzh=#{GRZH}</script>")
    List<Map> getListByName(String GRZH);

    /**
     * 还款计算
     * @param map
     * @return
     */
    @Update("<script> update tb_repay set " +
            "MMONTH = #{MMONTH}," +
            "RESIDUE_PERIODS = #{RESIDUE_PERIODS}," +
            "REPAYED_MONEY = #{REPAYED_MONEY}, " +
            "REPAYED_PERIODS = #{REPAYED_PERIODS}," +
            "RESIDUE_ALL_MONEY= #{RESIDUE_ALL_MONEY}," +
            "REPAYED_INTERESTS = #{REPAYED_INTERESTS}," +
             "RESIDUE_MONEY = #{RESIDUE_MONEY}, " +
            "RESIDUE_INTERESTS = #{RESIDUE_INTERESTS}," +

            "ALL_MONEY=#{ALL_MONEY}," +
            "REPAY_INTERESTS=#{REPAY_INTERESTS}," +
            "REPAY_MONTH=#{MONTH}," +
            "REPAYED_All_MONEY=#{REPAYED_ALL_MONEY} ," +
            "REPAY_MONTH_MONEY=#{REPAY_MONTH_MONEY}" +
            "where GRZH = #{GRZH} </script>")
     int archiveRepay1(Map map);
    /*select loan_id,pid,grzh,pname,loan_money,loan_periods," +
                "c_time,loan_rate，repay_bank,repay_account,loan_repay,repay_money," +
                "repay_interests,repayed_money,repayed_interests,repayed_all_money," +
                "residue_money,residue_interests,residue_periods,repayed_periods," +
                "repay_money,repayed_date,repay_status,repay_month_money," +
                "repay_month_interests,mmonth from tb_repay*/

    /**
     * 查询还款表全部信息
     * @param map
     * @return
     */
    @Select("<script>select * from tb_repay</script>")
    int archiveRepay(Map map);




    /**
     * 查询个人的还款记录
     * @param GRZH
     * @return
     *//*select b.repayed_all_money,a.grzh,a.pname,a.repay_month_money,a.repayed_date,b.residue_periods,b.repayed_money,b.repayed_periods," +
            "b.residue_money,b.residue_periods from tb_repay_record a \n" +
            "left  join tb_repay b on a.grzh=b.grzh*/
        @Select("<script> select grzh,pname,repay_month,repayed_date,repayed_periods," +
                "REPAY_MONTH_INTERRSTS,REPAY_MONEY," +
                "residue_periods from tb_repay_record where GRZH = #{GRZH}</script>")
    List<Map> getRecordByName(String GRZH);

    /**
     * 插入还款记录
     * @param map
     * @return
     */
    @Insert("insert into tb_repay_record (record_id,grzh,pname,repayed_date,REPAYED_PERIODS,REPAY_MONTH ,REPAY_MONEY,REPAY_MONTH_INTERRSTS)" +
            "values(SEQ_RECORD.nextval,#{GRZH},#{PNAME},to_date(sysdate)," +
            "#{REPAYED_PERIODS}+1，#{REPAY_MONTH},#{REPAY_MONEY},#{REPAY_MONTH_INTERRSTS})")
    int insertRecord(Map map);


    /**
     * 提前还款
     * @param map
     * @return
     */
    @Select("<script>select * from tb_repay  where GRZH = #{GRZH}</script>")
    List<Map> tiqian(Map map);

    /**
     * 逾期还款
     * @param map
     * @return
     */
 @Select("select * from tb_repay where state='逾期'")
 int yuqi(Map map);
}
