package com.aaa.sb.service;

import com.aaa.sb.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:PersonService
 * discription:
 * author:Dbailing
 * createTime:2018-12-13 14:49
 */

public interface PersonService {

    List<Map> getList();
}
