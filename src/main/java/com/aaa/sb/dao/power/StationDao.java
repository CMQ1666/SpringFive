package com.aaa.sb.dao.power;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:StationDao
 * discription:
 * author:cmq
 * createTime:2018-12-27 20:52
 */

public interface StationDao {

    /**
     * 获得后台用户岗位的数据
     * @param map
     * @return
     */
    @Select(value = "select * from tb_user")
    List<Map> getList(Map map);

    /**
     * 添加岗位
     * @param map
     * @return
     */
    @Insert(value = "insert into tb_user(ID,NAME,PASSWORD,ROLEID,GW,PHONE) values(seq_userAccount_id.nextval,#{NAME},#{PASSWORD},#{ROLEID},#{GW},#{PHONE})")
    int addStation(Map map);

    /**
     * 更新用户表
     * @param map
     * @return
     */
    @Update(value = "update tb_user set NAME=#{NAME},PASSWORD=#{PASSWORD},ROLEID=#{ROLEID},GW=#{GW},PHONE=#{PHONE} where ID=#{ID}")
    int updateStation(Map map);

    /**
     * 删除用户表
     * @param map
     * @return
     */
    @Delete(value = "delete from TB_USER where ID=#{ID}")
    int deleteStation(Map map);
}
