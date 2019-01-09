package com.aaa.sb.dao.Gjjtq;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface YdtqDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script> select a.*,b.* from " +
            "TB_AGREED_TO_EXTRACT a left join TB_REPAY b  on a.PERACCSTATE1=b.LOAN_ID " +
            "<where><if test=\"PNAME!=null and PNAME!=''\"> and PNAME=#{PNAME}</if> </where></script>")
    List<Map> getPageByParam(Map map);

}
