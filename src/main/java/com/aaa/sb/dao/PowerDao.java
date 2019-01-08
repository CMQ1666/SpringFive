package com.aaa.sb.dao;

import com.aaa.sb.entity.Node;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * className:PowerDao
 * discription:
 * author:zz
 * createTime:2018-12-11 09:22
 */
public interface PowerDao {

    /**
     * 权限列表查询
     * @return
     */
    @Select("select id, label as label, iconcls as iconClass, url, parentid pid from power")
    List<Node> getList();
}
