package com.aaa.sb.service.power;

import com.aaa.sb.entity.Node;

import java.util.List;
import java.util.Map;

/**
 * className:PowerService
 * discription:
 * author:zz
 * createTime:2018-12-11 09:35
 */
public interface PowerService {

    /**
     * 列表查询方法
     * @return
     */
    List<Node> getList();

    /**
     * 权限树增加
     * @param map
     * @return
     */
    int treeAdd(Map map);

    /**
     * 权限树修改
     * @param map
     * @return
     */
    int treeUpdate(Map map);

    /**
     * 权限树删除
     * @param map
     * @return
     */
    int treeDetele(Map map);

    /**
     * 查找父ID
     * @param map
     * @return
     */
    List<Map> getListById(Map map);
}
