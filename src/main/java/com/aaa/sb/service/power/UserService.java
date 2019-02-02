package com.aaa.sb.service.power;


import com.aaa.sb.entity.User;

import java.util.List;
import java.util.Map;

/**
 * className:UserService
 * discription:
 * author:Dbailing
 * createTime:2018-12-06 22:00
 */

public interface UserService {

    public User findByName(String name);

    public User findById(Integer id);

    List<Map> findByAll(String name);
}
