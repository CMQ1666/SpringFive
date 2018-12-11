package com.aaa.sb.dao;

import com.aaa.sb.entity.User;

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
}
