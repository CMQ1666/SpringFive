package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;
import java.util.List;

public interface FeePayableDao {
    @Select("<script>select a.UNID,DWZH,UNAME,UDEPOSITRATIO,UPERSONRATIO,UDEPOSITEDPNUM,UAREMAIN,UAOWEMONERY,UASTATE,to_char(UAPAYENDDATE,'yyyy-MM-dd') as UAPAYENDDATE \n"+
            "from tb_unitaccount a left join tb_unit b on a.UNID=b.UNID <where><if test=\"UNAME!=null and UNAME!=''\"> and UNAME like '%'||#{UNAME}||'%'</if></where></script> ")
    List<Map> getList(Map map);
    /**
     * 查询公司分页数据
     *
     * @param map
     * @return
     */
    @Select("<script>select a.GRZH,a.DALANCE,a.PERACCSTATE,a.UBITNROP,a.INDINROP,a.UNITMONPAYSUM,a.PERMONPAYSUM,a.YINTERESTBAL,b.PNAME from tb_paccountutil a \n" +
            "join tb_person_info b on a.PID= b.PID</script>")
    List<Map> getList2(Map map);
    /**
     * 根据公司账号查询数据
     *
     * @param map
     * @return
     */
    @Select("select a.UNID,a.DWZH,b.UNAME,to_char(sysdate,'yyyy-mm-dd') as UAPAYENDDATE,((extract (year from sysdate)-substr(#{UAOWEMONTHS},0,4))*12+extract(month from sysdate))- substr(#{UAOWEMONTHS},6,2) as UAOWEMONTHS,a.UDEPOSITRATIO,a.UASTATE,\n" +
            "a.UPERSONRATIO,a.UAREMAIN,a.UDEPOSITEDPNUM,a.UAOWEMONERY from tb_unitaccount a \n" +
            "left join tb_unit b on a.UNID = b.UNID where DWZH = #{DWZH}")
    Map getList1(Map map);

    /**
     * 汇缴成功时提交汇缴日期
     *
     * @param map
     * @return
     */
    @Update("update tb_unitaccount set UAPAYENDDATE = to_date(#{UAPAYENDDATE},'yyyy-mm-dd') where DWZH = #{DWZH}")
    int update(Map map);

    /**
     * 汇缴成功时单位余额当减少
     *
     * @param map
     * @return
     */
    @Update("update tb_unitaccount set UAREMAIN = (#{UAREMAIN}- #{UAOWEMONERY}) where DWZH = #{DWZH}")
    int update1(Map map);

    /**
     * 汇缴成功时更改个人汇缴日期
     *
     * @param map
     * @return
     */
    @Update("update tb_paccountutil set LASTNAYDATE = to_date(#{UAPAYENDDATE},'yyyy-mm-dd') where  UNID = #{UNID}")
    int update2(Map map);

    /**
     * 查询账户表中的数据
     *
     * @param map
     * @return
     */
    @Select("select PNAME,GRZH,DALANCE,LASTNAYDATE,PERMONPAYSUM,YDRAWAMT from tb_paccountutil a left join " +
            "tb_person_info b on a.pid = b.pid where UNID=#{UNID}")
    List<Map> select1(Map map);

    /**
     * 查询
     * @param map
     * @return
     */
    @Select("select UNAME,DWZH,UAREMAIN,UAPAYENDDATE,UAOWEMONERY from tb_unitaccount a left join tb_unit b on a.UNID = b.UNID where DWZH = #{DWZH}")
    List<Map> select2(Map map);

    /**
     * 根据个人账号更新账户余额
     *
     * @param map
     * @return
     */
    @Update("update tb_paccountutil set DALANCE = (#{DALANCE} + #{YDRAWAMT}*#{uaowemonths}) where GRZH = #{GRZH} and PERACCSTATE = '正常'")
    int update3(Map map);

    /**
     * 查询监听键盘事件所需要的数据
     *
     * @param id
     * @return
     */
    @Select("select UAOWEMONERY from tb_unitaccount where DWZH = #{DWZH}")
    Map getById(String id);

    /**
     * 向单位记录表插入数据
     * @param map
     * @return
     */
    @Insert("insert into urecord (ID,UNAME,UACCOUNT,UMONEY,UTYPE,UDATE,UCMONEY) values(seq_urecord_id.nextval，#{UNAME},#{DWZH},#{UAREMAIN},'补缴',#{UAPAYENDDATE},#{UAOWEMONERY})")
    int insert(Map map);

    /**
     * 向个人记录表插入数据
     * @param map
     * @return
     */
    @Insert("insert into precord (ID,PNAME,PACCOUNT,PMONEY,PTYPE,PDATE,PCMONEY,PMONTH) values(seq_precord_id.nextval,#{PNAME},#{GRZH},(#{DALANCE}+#{YDRAWAMT}*#{uaowemonths}),'补缴',#{LASTNAYDATE},#{PERMONPAYSUM},#{UAOWEMONTHS})")
    int insert1(Map map);
}

