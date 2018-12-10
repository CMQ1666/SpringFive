package com.aaa.sb.service;

import com.aaa.sb.dao.UserMapper;
import com.aaa.sb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * className:UserServiceImpl
 * discription:
 * author:cmq
 * createTime:2018-12-06 21:21
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByName(String name) {

        return userMapper.findByName(name);
    }

    @Override
    public User findById(Integer id) {

        return userMapper.findById(id);
    }
}
