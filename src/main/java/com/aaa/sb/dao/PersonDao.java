package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * className:PersonDao
 * discription:
 * author:Dbailing
 * createTime:2018-12-12 20:55
 */

public interface PersonDao {
    /**
     * 获得分页数据
     * @return
     */
    @Select("<script> select a.pname,a.idnumber,b.dalance,b.peraccState from tb_person_info a,tb_paccountutil b where a.pid=b.pid\n" +
            "<where><if test=\"pname!=null and pname!=''\"> and pname like '%'||#{pname}||'%'</if></where></script>")
    List<Map> getList();

}
