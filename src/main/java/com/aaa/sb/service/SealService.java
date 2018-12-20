package com.aaa.sb.service;

import com.aaa.sb.entity.PersonsAccountNumberState;

import java.util.List;
import java.util.Map;

/**
 * className:SealService
 * discription:
 * author:Dbailing
 * createTime:2018-12-19 20:54
 */

public interface SealService {
    /**
     * 封存、启封、销户 分页
     * @param map
     * @return
     */
    List<Map> SealedPage(Map map);


}
