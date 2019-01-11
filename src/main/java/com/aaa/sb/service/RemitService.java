package com.aaa.sb.service;

import java.util.Map;
import java.util.List;

public interface RemitService {

    List<Map> getList(Map map);

    Object add(Map map);
    Object update(Map map);
}
