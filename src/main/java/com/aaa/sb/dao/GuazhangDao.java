package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface GuazhangDao {

    @Select("<script> select t2.UATEBALANCE,t2.DWZH,t1.UNAME,t2.YWBLR,t2.UDEPOSITRATIO,t2.UAOWEMONTHS,t2.UPERSONRATIO,t2.UDEPOSITEDPNUM,t2.UAREMAIN,t2.UAOWEMONERY,t2.UASTATE,t2.unid,to_char(t2.uapayenddate,'yyyy-MM-dd') as uapayenddate "+
            " from tb_unit t1 left join tb_unitaccount t2 on t1.unid=t2.unid \n"+
            " <where><if test=\"UNAME!=null and UNAME!=''\"> and UNAME like '%'||#{UNAME}||'%'</if></where></script>")
    List<Map> getList(Map map);
//    @Insert("insert into tb_unitaccount(UAREMAIN,ZCK)values(#{UAREMAIN},#{ZCK}) where DWZH=#{DWZH} ")
//    int add(Map map);
    @Update("update tb_unitaccount set UAREMAIN=UAREMAIN+#{UATEBALANCE},UATEBALANCE=#{UATEBALANCE} where DWZH=#{DWZH}")
    int update(Map map);
}
