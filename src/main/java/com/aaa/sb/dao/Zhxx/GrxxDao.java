package com.aaa.sb.dao.Zhxx;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface GrxxDao {


    /**
     * 分页查询
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script> select rownum rn,b.PID,b.PNAME,b.PHONE,b.PSEX,b.IDCARD,b.IDNUMBER,b.PDATE,b.PMARRIAGE,b.PROFESSION,b.EDUCATION,b.PEMAIL,b.PADDRESS,b.UNIT_ID,b.STATE,a.*,c.* from tb_paccountutil a join tb_person_info b on a.pid=b.pid join tb_unit c on a.unid=c.unid\n" +
            "<where><if test=\"PNAME!=null and PNAME!=''\"> and PNAME like '%'||#{PNAME}||'%'</if></where></script>")
    List<Map> getPageByParam(Map map);




    /**
     * 修改个人账户信息
     * @param map
     * @return
     */
    @Update("update tb_person_info set PNAME = #{PNAME},PHONE=#{PHONE},PSEX=#{PSEX},IDNUMBER=#{IDNUMBER},PMARRIAGE=#{PMARRIAGE},PROFESSION=#{PROFESSION},IDCARD=#{IDCARD},EDUCATION=#{EDUCATION},PEMAIL=#{PEMAIL},PADDRESS=#{PADDRESS} where PID=#{PID}")
    int upadte(Map map);

}
