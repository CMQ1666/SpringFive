package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;
import java.util.List;

public interface RushToPayDao {
    @Select("<script>select t4.PACCID,t5.PNAME,t1.UNAME,t4.YDRAWAMT,t3.UBDCREATEDATE from tb_unit t1 left join tb_unitaccount t2 on t1.unid=t2.unid \n"+
    "left join tb_unitbizdetail t3 on t3.uaid=t2.uaid left join tb_paccountutil t4 on t4.unid=t1.unid left join tb_person_info t5 on t5.pid=t4.pid \n"+
    "<where><if test=\"UNAME!=null and UNAME!=''\"> and UNAME like '%'||#{UNAME}||'%'</if></where></script>")
    List<Map> getList(Map map);
    @Update("update tb_paccountutil set YDRAWAMT=#{YDRAWAMT} where PACCID=#{PACCID}")
    int update(Map map);
    @Select("select t4.PACCID,t5.PNAME,t1.UNAME,t4.YDRAWAMT,t3.UBDCREATEDATE from tb_unit t1 left join tb_unitaccount t2 on t1.unid=t2.unid \n"
            + "left join tb_unitbizdetail t3 on t3.uaid=t2.uaid left join tb_paccountutil t4 on t4.unid=t1.unid left join tb_person_info t5 on t5.pid=t4.pid "+
    "where where PACCID=#{PACCID}")
    List<Map> getList1(Map map);
}
