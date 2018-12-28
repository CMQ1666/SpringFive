package com.aaa.sb.dao.power;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:RoleDao
 * discription:
 * author:cmq
 * createTime:2018-12-27 19:06
 */
public interface RoleDao {

    /**
     * 获取角色列表
     * @param map
     * @return
     */
    @Select("<script> select ROLEID,ROLENAME from tb_role \n" +
            "<where> <if test=\"ROLENAME!=null and ROLENAME!=''\"> and ROLENAME like '%'||#{ROLENAME}||'%'</if></where></script>")
    List<Map> getList(Map map);

    /**
     * 获取角色下拉框查询数据
     * @return
     */
    @Select(value = "select ROLEID,ROLENAME from tb_role")
    List<Map>  getRoleList();

    /**
     * 添加角色
     * @param map
     * @return
     */
    @Insert(value = "insert into tb_role(ROLEID,ROLENAME) values(seq_Role_power.nextval,#{ROLENAME})")
    int inRole(Map map);

    /**
     * 更新角色
     * @param map
     * @return
     */
    @Insert(value = "update tb_role set ROLENAME=#{ROLENAME} where ROLEID=#{ROLEID}")
    int updRole(Map map);

    /**
     * 删除角色
     * @param map
     * @return
     */
    @Delete(value = "delete from tb_role where ROLEID=#{ROLEID}")
    int deleteRole(Map map);

    /**
     * 批量删除
     * @param ROLEID
     * @return
     */
    @Delete(value = "delete from tb_role where ROLEID=#{ROLEID}")
    int deleteManyRole(Integer ROLEID);
}
