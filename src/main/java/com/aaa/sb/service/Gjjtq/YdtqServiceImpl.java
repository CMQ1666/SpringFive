package com.aaa.sb.service.Gjjtq;

import com.aaa.sb.dao.Gjjtq.YdtqDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class YdtqServiceImpl implements YdtqService {
    @Autowired
    private YdtqDao ydtqDao;
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Override
    public List<Map> getPageByParam(Map map) {
        return ydtqDao.getPageByParam(map);
    }


}
