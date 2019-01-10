package com.aaa.sb.service.Qtgl;

import com.aaa.sb.dao.Qtgl.ZcxgFbDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ZcxgFbServiceImpl implements ZcxgFbService {

    @Autowired
    private ZcxgFbDao zcxgFbDao;

    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Override
    public List<Map> getPageByParam(Map map) {
        return zcxgFbDao.getPageByParam(map);
    }
    /**
     * 添加新闻
     * @param map
     * @return
     */
    @Override
    public int add(Map map) {
        return zcxgFbDao.add(map);
    }

    /**
     * 更新新闻
     * @param map
     * @return
     */
    @Override
    public int update(Map map) {
        return zcxgFbDao.upadte(map);
    }

    /**
     * 删除新闻
     * @param empNo
     * @return
     */
    @Override
    public int delete(int empNo) {
        return zcxgFbDao.delete(empNo);
    }

    /**
     * 批量删除新闻
     * @param _s
     * @return
     */
    @Override
    public int batchDel(String _s) {
        return zcxgFbDao.batchDel(_s);
    }
}
