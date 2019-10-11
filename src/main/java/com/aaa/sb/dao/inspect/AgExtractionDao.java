package com.aaa.sb.dao.inspect;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 通过审核
     * @param map
     * @return
     */
    @Update(value = "update tb_agreed_to_extract set SHZT=2 where BID=#{BID}")
    int pass(Map map);
    /**
     * 驳回审核
     * @param map
     * @return
     */
    @Update(value = "update tb_agreed_to_extract set SHZT=3 where BID=#{BID}")
    int twoPass(Map map);
}
