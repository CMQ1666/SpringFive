package com.aaa.sb.service;

import java.util.Map;
import java.util.List;

public interface BaseAdjustmentService {
    List<Map> getList(Map map);
    List<Map> getList1(Map map);
    List<Map> getList2(String map);
    Map update(Map map);
}
