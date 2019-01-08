package com.aaa.sb.dao.Zhxx;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface DwxxDao {

    /**
     * 分页查询
     * @param
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script> select rownum rn,a.*,to_char(UAPAYENDDATE,'yyyy-mm-dd') as UAPAYENDDATE,to_char(KHRQ,'yyyy-mm-dd') as KHRQ,b.* from tb_unit a join tb_unitaccount b on a.unid=b.unid\n" +
            "<where><if test=\"UNAME!=null and UNAME!=''\"> and a.UNAME like '%'||#{UNAME}||'%'</if></where></script>")
    List<Map> getPageByParam(Map map);




    /**
     * 更新单位信息
     * @param map
     * @return
     */
    @Update("update tb_unit set UNAME = #{UNAME},UADDRESS=#{UADDRESS},ULEGALPERSON=#{ULEGALPERSON},ULEGALTYPE=#{ULEGALTYPE},ULEGALCARD=#{ULEGALCARD},GSLX=#{GSLX} where UNID=#{UNID}")
    int upadte(Map map);


}
