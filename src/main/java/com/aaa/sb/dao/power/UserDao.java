package com.aaa.sb.dao.power;

import com.aaa.sb.entity.User;

import java.util.List;
import java.util.Map;

/**
 * className:UserDao
 * discription:
 * author:Dbailing
 * createTime:2018-12-08 17:06
 */

public interface UserDao {
    /**
     * 根据用户名查询密码
     * @param name
     * @return
     */
    public User findByName(String name);

    /**
     * 查询认证消息
     * @param id
     * @return
     */
    public User findById(Integer id);

    /**
     * 通过用户名查询该用户所有信息
     * @param name
     * @return
     */

    List<Map> findByAll(String name);
}
