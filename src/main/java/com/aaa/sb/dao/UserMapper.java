package com.aaa.sb.dao;

import com.aaa.sb.entity.User;
import org.apache.ibatis.annotations.Select;

/**
 * className:UserMapper
 * discription:
 * author:cmq
 * createTime:2018-12-06 21:15
 */
public interface UserMapper {
    @Select("select id,name,password from userss where name=#{NAME}")
    User findByName(String name);

    @Select("select id,name,password,perms from userss where id=#{ID}")
    User findById(Integer id);
}
