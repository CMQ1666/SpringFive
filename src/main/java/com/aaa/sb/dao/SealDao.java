package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:SealDao
 * discription:
 * author:Dbailing
 * createTime:2018-12-19 18:49
 */

public interface SealDao {
    /**
     * 封存 启封 销户 分页
     * @param map
     * @return
     */
    @Select("<script>select a.GRZH,a.ubitnrop,a.indinrop,a.pid,b.pid,a.basenummber,a.dalance,a.lastnaydate,a.peraccstate,b.pname from tb_paccountutil a,tb_person_info b where a.pid=b.pid" +
            "<where><if test=\"pname!=null and pname!=''\"> and pname like '%'||#{pname}||'%'</if></where></script>")
    List<Map> SealedPage(Map map);
}
