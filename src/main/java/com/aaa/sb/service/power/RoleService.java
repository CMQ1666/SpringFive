package com.aaa.sb.service.power;

import java.util.List;
import java.util.Map;

/**
 * className:RoleService
 * discription:
 * author:cmq
 * createTime:2018-12-27 19:08
 */
public interface RoleService {

    /**
     * 获取角色列表
     * @param map
     * @return
     */
    List<Map> getList(Map map);
    /**
     * 获取角色下拉框查询数据
     * @param map
     * @return
     */
    List<Map>  getRoleList();
    /**
     * 添加角色
     * @param map
     * @return
     */
    int inRole(Map map);
    /**
     * 更新角色
     * @param map
     * @return
     */
    int updRole(Map map);
    /**
     * 删除角色
     * @param map
     * @return
     */
    int deleteRole(Map map);

    /**
     * 批量删除
     * @param
     * @return
     */
    int deleteManyRole(String ids);
}
