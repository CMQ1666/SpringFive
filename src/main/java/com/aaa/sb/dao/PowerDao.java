package com.aaa.sb.dao;

import com.aaa.sb.entity.TreeNode;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:PowerDao
 * discription:
 * author:Dbailing
 * createTime:2018-12-08 19:02
 */

public interface PowerDao {
    @Select("select * from power")
    /**
     * 获取权限数据
     * @return
     */
    List<TreeNode> getList();
}
