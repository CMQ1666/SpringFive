package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Map;
public interface CompanyDao {

    @Insert("insert into tb_unit(unid,UCREATER,UNAME,UADDRESS,ULEGALPERSON,ULEGALTYPE,ULEGALCARD,UPAYDATE,UOPERATOR,UOPIPHONE,JBRZJLX,JBRZJHM,UNITBEGINDATE,UNETWORKCODE,GSLX,SSHY,LSGX,JJLX,YYZZ,DWXZ) values(seq_tb_unit.nextval,#{UCREATER},#{UNAME},#{UADDRESS},#{ULEGALPERSON},#{ULEGALTYPE},#{ULEGALCARD},#{UPAYDATE},#{UOPERATOR},#{UOPIPHONE},\n" +
            "#{JBRZJLX},#{JBRZJHM},to_date(substr(#{UNITBEGINDATE},1,10),'yyyy-MM-dd'),#{UNETWORKCODE},#{GSLX},#{SSHY},#{LSGX},#{JJLX},#{YYZZ}, \n" +
            "extract(year from sysdate)||extract(month from sysdate)||extract(day from sysdate)||to_char(seq_tb_unit.nextval,'fm00000'))")
    int add(Map map);

    /**
     * 添加方法
     * @param map
     * @return
     */
    @Insert("insert into TB_UNITACCOUNT(uaid,UDEPOSITRATIO,UPERSONRATIO,UASTATE,UABANKNAME,UABANKNUMBER,STYH,YWBLR,KHRQ,DWZH,UNID,UAOWEMONTHS,UAREMAIN) values(seq_tb_unitaccount.nextval,\n" +
            "#{UDEPOSITRATIO},#{UPERSONRATIO},#{UASTATE},#{UABANKNAME},#{UABANKNUMBER},#{STYH},#{YWBLR},to_date(substr(#{KHRQ},1,10),'yyyy-MM-dd'), \n" +
            "extract(year from sysdate)||extract(month from sysdate)||extract(day from sysdate)||to_char(seq_tb_unitaccount.nextval,'fm00000'),seq_tb_unit.currval,1,0)")
    int add1(Map map);
    @Select("select count(*) from tb_unit where UNAME = #{name}")
    int uname(String name);
}
