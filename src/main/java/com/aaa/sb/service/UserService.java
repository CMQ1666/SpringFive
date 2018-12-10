package com.aaa.sb.service;

import com.aaa.sb.entity.User;

/**
 * className:UserService
 * discription:
 * author:cmq
 * createTime:2018-12-06 21:20
 */
public interface UserService {
    public User findByName(String name);

    User findById(Integer id);
}
