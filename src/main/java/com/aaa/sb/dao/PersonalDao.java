package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.List;

@Component
public interface PersonalDao {
    /**
     * 个人账户注册
     * @param map
     * @return
     */
    @Insert("insert into tb_person_info (PID,EDUCATION,PROFESSION,PNAME,PEMAIL,PHONE," +
            "PADDRESS,PSEX,PMARRIAGE,IDCARD,IDNUMBER,STATE,UNIT_ID,PDATE) values(seq_person_id.nextval,#{EDUCATION}," +
            "#{PROFESSION},#{PNAME},#{PEMAIL},#{PHONE},#{PADDRESS},#{PSEX}," +
            "#{PMARRIAGE},#{IDCARD},#{IDNUMBER},'良好',#{UNID},#{PDATE})")
    int add(Map map);
    /**
     * 向账户表添加信息
     * @param map
     * @return
     */
    @Insert("insert into tb_paccountutil (PACCID,OPENDATE,PERACCSTATE,BASENUMMBER,UBITNROP,INDINROP,PAOP,PID,KHYH,YHZH,GRMM,UNITMONPAYSUM" +
            ",PERMONPAYSUM,YDRAWAMT,GRZH,UNID,LASTNAYDATE,YPAYAMT,YINTERESTBAL) values(seq_paccount_id.nextval,SYSDATE,'正常',#{BASENUMMBER},#{UDEPOSITRATIO},#{UPERSONRATIO},#{PAOP},seq_person_id.currval," +
            "#{KHYH},#{YHZH},'123456',#{BASENUMMBER}*#{UDEPOSITRATIO}*0.01,#{BASENUMMBER}*#{UPERSONRATIO}/100,#{UDEPOSITRATIO}*#{BASENUMMBER}/100+#{UPERSONRATIO}*#{BASENUMMBER}/100,to_char(seq_paccount_id.nextval,'fm0000000'),#{UNID},sysdate,'测试',1)")
    int add1(Map map);
    /**
     * 根据id查询数据
     * @param map
     * @return
     */
    @Select("select b.UNAME,a.UDEPOSITRATIO,a.UPERSONRATIO,a.UNID from tb_unitaccount a join tb_unit b on a.unid = b.unid where DWZH =#{DWZH}")
     Map getUnitInfo(Map map);
    /**
     *个人注册成功时更改公司人数
     * @param map
     * @return
     */
    @Update("update TB_UNITACCOUNT set UDEPOSITEDPNUM = (select count(*) from tb_person_info where UNIT_ID = #{UNID}) where DWZH = (select DWZH from  tb_unitaccount where UNID=#{UNID})")
    int update(Map map);

    /**
     * 个人注册成功时更改公司的缴纳金额
     * @param map
     * @return
     */
    @Update("update TB_UNITACCOUNT set UAOWEMONERY = (select sum(YDRAWAMT) from TB_PACCOUNTUTIL where unid = #{UNID}) WHERE UNID = #{UNID}")
    int update1(Map map);

    @Select("select unid from TB_UNITACCOUNT where DWZH=#{DWZH}")
    List<Map> getUnidByParam(Map map);
}

