package com.aaa.sb.service.power;


import com.aaa.sb.dao.power.UserDao;
import com.aaa.sb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * className:UserServiceImpl
 * discription:
 * author:Dbailing
 * createTime:2018-12-06 22:00
 */
@Service
public class UserServiceImpl implements  UserService {
   @Autowired
    private UserDao userDao;


    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userDao.findByName(name);
    }
}
