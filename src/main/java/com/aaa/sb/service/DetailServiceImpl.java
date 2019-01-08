package com.aaa.sb.service;

import com.aaa.sb.dao.Detaildao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:DetailServiceImpl
 * discription:
 * author:zz
 * createTime:2018-12-19 15:16
 */
@Service
public class DetailServiceImpl implements DetailService {
    @Autowired
    private Detaildao detail;
    @Override
    public List<Map> getList() {
        return detail.getList();
    }
}
