package com.aaa.sb.service.Zhxx;

import com.aaa.sb.dao.Zhxx.GrxxDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GrxxServiceImpl implements GrxxService{

    @Autowired
    private GrxxDao grxxDao;
    /**
     * 分页查询
     * @return
     */
    @Override
    public List<Map> getPageByParam(Map map) {
        return grxxDao.getPageByParam(map);
    }


    /**
     * 修改个人信息
     * @param map
     * @return
     */
    @Override
    public int update(Map map) {
        return grxxDao.upadte(map);
    }


}
