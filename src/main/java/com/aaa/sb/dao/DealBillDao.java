package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.Map;
import java.util.List;

public interface DealBillDao {
    @Select("<script>select t2.DWZH,t1.UNAME,t2.UDEPOSITRATIO,t2.UPERSONRATIO,t2.UDEPOSITEDPNUM,t2.UAREMAIN,t2.UAOWEMONERY,t2.UASTATE,t2.UAPAYENDDATE FROM  TB_UNIT t1 left join TB_UNITACCOUNT t2 on t1.unid = t2.unid \n"+
    "<where><if test=\"UNAME!=null and UNAME!=''\"> and UNAME like '%'||#{UNAME}||'%'</if></where></script>")
   List<Map> getList(Map map);
    @Update("update tb_unitaccount set uARemain=uARemain+#{GZJE} where DWZH=#{DWZH} ")
    int update(Map map);
}
