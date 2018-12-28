package com.aaa.sb.service.power;

import com.aaa.sb.dao.power.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:RoleServiceImpl
 * discription:
 * author:cmq
 * createTime:2018-12-27 19:09
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Map> getList(Map map) {
        return roleDao.getList(map);
    }

    @Override
    public List<Map> getRoleList() {
        return roleDao.getRoleList();
    }

    @Override
    public int inRole(Map map) {
        return roleDao.inRole(map);
    }

    @Override
    public int updRole(Map map) {
        return roleDao.updRole(map);
    }

    @Override
    public int deleteRole(Map map) {
        return roleDao.deleteRole(map);
    }

    @Override
    public int deleteManyRole(String ids) {
        int i= 0;
        String[] oids = ids.split(",");
        for(String ROLEID:oids){
            i = roleDao.deleteManyRole(Integer.valueOf(ROLEID));
        }
        return i;
    }


}
