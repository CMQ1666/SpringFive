package com.aaa.sb.dao.inspect;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:ExtractionDao
 * discription:提取审核页面
 * author:cmq
 * createTime:2018-12-24 09:40
 */
public interface ExtractionDao {
    /**
     * 获得提取数据
     * @return
     */
    @Select("<script> select * from tb_extract_application \n" +
            "<where> appl_state=1 and APPLICATION_TYPE=#{radio} <if test=\"PNAME!=null and PNAME!=''\"> and PNAME like '%'||#{PNAME}||'%'</if></where></script>")
    List<Map> getList(Map map);

    /**
     * 根据个人账号查提取申请表信息
     * @param map
     * @return
     */
    @Select(value = "select * from TB_EXTRACT_APPLICATION where PRE_ACCOUNT=#{PRE_ACCOUNT} ")
    List<Map> getCount(Map map);

    /**
     * 根据个人账号通过申请
     * @param map
     * @return
     */
    @Update(value = "update TB_EXTRACT_APPLICATION set APPL_STATE=2 where PRE_ACCOUNT=#{PRE_ACCOUNT}")
    int updPass(Map map);

    /**
     * 提取余额
     * @param map
     * @return
     */
    @Update(value = "update TB_PACCOUNTUTIL set DALANCE=DALANCE-#{EXTRACT_MONEY} where GRZH=#{PRE_ACCOUNT}")
    int updMinus(Map map);

    /**
     * 驳回申请
     * @param map
     * @return
     */
    @Update(value = "update TB_EXTRACT_APPLICATION set APPL_STATE=0 where PRE_ACCOUNT=#{PRE_ACCOUNT}")
    int updLose(Map map);

    /**
     * 销户
     * @return
     */
    @Update(value = "update TB_PACCOUNTUTIL set DALANCE=0,PERACCSTATE='销户' where GRZH=#{PRE_ACCOUNT}")
    int udpDelete(Map map);

    /**
     * 销户成功
     * @param map
     * @return
     */
    @Update(value = "update TB_EXTRACT_APPLICATION set APPL_STATE=2 where PRE_ACCOUNT=#{PRE_ACCOUNT}")
    int udpShXh(Map map);

}
