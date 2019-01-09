package com.aaa.sb.service.Zhxx;

import com.aaa.sb.dao.Zhxx.DwxxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DwxxServiceImpl implements DwxxService {

    @Autowired
    private DwxxDao dwxxDao;
    /**
     * 分页查询
     * @param
     * @return
     */
    @Override
    public List<Map> getPageByParam(Map map) {
        return dwxxDao.getPageByParam(map);
    }



    /**
     * 更新单位信息
     * @param map
     * @return
     */
    @Override
    public int update(Map map) {
        return dwxxDao.upadte(map);
    }
}
