package com.aaa.sb.dao;

import com.aaa.sb.entity.User;

/**
 * className:UserDao
 * discription:
 * author:Dbailing
 * createTime:2018-12-08 17:06
 */

public interface UserDao {

    public User findByName(String name);
    public User findById(Integer id);
}
