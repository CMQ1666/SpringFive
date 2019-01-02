package com.aaa.sb.service.Gjjtq;

import com.aaa.sb.dao.Gjjtq.BfxhYdtqDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BfxhYdtqServiceImpl implements BfxhYdtqService{
    @Autowired
    private BfxhYdtqDao bfxhYdtqDao;

    /**
     * 带参分页查询
     * @param map
     * @return
     */
    @Override
    public List<Map> getPageByParam(Map map) {
        return bfxhYdtqDao.getPageByParam(map);
    }

    @Override
    public int add(Map map) {
        Object EXTRACT_REASON=map.get("EXTRACT_REASON");
        if (EXTRACT_REASON !=null && !EXTRACT_REASON.equals("")){
            if (EXTRACT_REASON.equals("偿还购房贷款本息")){
                return bfxhYdtqDao.addDaikuan(map);
            }else{
                return bfxhYdtqDao.add(map);
            }
        }
        return 0;
    }

    @Override
    public int addDaikuan(Map map) {

        return bfxhYdtqDao.addDaikuan(map);
    }

}
