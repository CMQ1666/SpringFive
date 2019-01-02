package com.aaa.sb.dao.inspect;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:AgExtractionDao
 * discription:
 * author:cmq
 * createTime:2018-12-21 09:39
 */
public interface AgExtractionDao {

    /**
     * 获得约定提取数据
     * @return
     */
    @Select("<script> select * from tb_agreed_to_extract \n" +
            "<where> SHZT=1 <if test=\"PNAME!=null and PNAME!=''\"> and PNAME like '%'||#{PNAME}||'%'</if></where></script>")
    List<Map> getList(Map map);
}
