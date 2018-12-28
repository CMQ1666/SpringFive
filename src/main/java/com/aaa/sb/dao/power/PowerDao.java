package com.aaa.sb.dao.power;

import com.aaa.sb.entity.Node;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:PowerDao
 * discription:
 * author:cmq
 * createTime:2018-12-11 09:22
 */
public interface PowerDao {

    /**
     * 权限树查询
     * @return
     */
    @Select("select id, label as label, iconcls as iconClass, url, parentid pid from power")
    List<Node> getList();



    /**
     * 权限树增加
     * @param map
     * @return
     */
    @Insert(value = "insert into power(ID,LABEL,PARENTID,URL) values(seq_powerid_xx.nextval,#{LABEL},#{PARENTID},#{URL})")
    int treeAdd(Map map);

    /**
     * 权限树修改
     * @param map
     * @return
     */
    @Update(value = "update power set LABEL=#{LABEL},PARENTID=#{PARENTID},URL=#{URL} where ID=#{ID}")
    int treeUpdate(Map map);

    /**
     * 权限树删除
     * @param map
     * @return
     */
    @Delete(value = "delete from power where ID=#{ID}")
    int treeDetele(Map map);

    /**
     * 查找父ID
     * @param map
     * @return
     */
    @Select(value = "select ID,LABEL from power where PARENTID=#{PARENTID}")
    List<Map> getListById(Map map);
}
