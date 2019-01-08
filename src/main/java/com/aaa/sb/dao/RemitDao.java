package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;
import java.util.List;
public interface RemitDao {

    @Select("<script> select t4.BASENUMMBER,t1.UNAME,t2.DWZH,t2.uDepositRatio,t2.uPersonRatio,t2.uARemain,t2.uaOweMonery,t2.uDepositedPnum,t2.uaState, t2.UAPAYENDDATE,t2.ywblr, t3.ubdCreateDate,t3.hjys,t3.ubdRemark from TB_UNIT t1 left join TB_UNITACCOUNT t2 on t1.unid = t2.unid  left join TB_UNITBIZDETAIL t3 on t3.uaid = t2.uaid left join TB_PACCOUNTUTIL t4 on t4.unid=t1.unid \n" +
            "<where><if test=\"UNAME!=null and UNAME!=''\"> and UNAME like '%'||#{UNAME}||'%'</if></where></script>")
        List<Map> getList(Map map);

    /**
     * 添加
     * @param map
     * @return
     */
    @Insert("insert into TB_UNITBIZDETAIL (UBID,DWZH,UBDPOPULATION,UBDDETAILTYPE,UBDPAYMENTDATE,UBDPAYWAY,ZCK,UBDSETTLESTATES,UBDCREATER,UBDCREATEDATE,HJYS) values(SEQ_TB_UNITBIZDETAIL.nextval,#{DWZH},#{UBDPOPULATION},'汇缴',#{UBDPAYMENTDATE},'均缴',#{ZCK},#{UBDSETTLESTATES},#{UBDCREATER},#{UBDCREATEDATE},#{HJYS})")
    int add(Map map);
    @Update("update tb_unitaccount set UAREMAIN=UAREMAIN-#{UAOWEMONERY} where DWZH=#{DWZH}")
    int update(Map map);
    @Update("update tb_paccountutil set DALANCE=#{YDRAWAMT}+DALANCE,YINTERESTBAL=1 where UNID=#(select UNID from tb_unitaccount where DWZH = #{DWZH}) and YINTERESTBal = 0 and PERACCSTATE = 1")
    int update1(Map map);

             }
