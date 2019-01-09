package com.aaa.sb.service.Gjjtq;

import com.aaa.sb.dao.Gjjtq.TqhdDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TqhdServiceImpl implements TqhdService{

    @Autowired
    private TqhdDao tqhdDao;
    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Override
    public List<Map> getPageByParam(Map map) {
        return tqhdDao.getPageByParam(map);
    }

    /**
     * 分页总数量
     * @param map
     * @return
     */
    @Override
    public int getPageCount(Map map) {
        return tqhdDao.getPageCount(map);
    }

    /**
     * 添加还贷人员
     * @param map
     * @return
     */
    @Override
    public int add(Map map) {
        return tqhdDao.add(map);
    }

    /**
     * 查看还贷记录
     * @param map
     * @return
     */
    @Override
    public int update(Map map) {
        return tqhdDao.upadte(map);
    }

    /**
     * 删除还贷人员
     * @param empNo
     * @return
     */
    @Override
    public int delete(int empNo) {
        return tqhdDao.delete(empNo);
    }

    /**
     * 批量删除还贷人员
     * @param _s
     * @return
     */
    @Override
    public int batchDel(String _s) {
        return tqhdDao.batchDel(_s);
    }

    /**
     * 查询提取还贷人员信息
     * @param map
     * @return
     */
    @Override
    public List<Map> getTqhd(Map map) {
        return tqhdDao.getTqhd(map);
    }
}
