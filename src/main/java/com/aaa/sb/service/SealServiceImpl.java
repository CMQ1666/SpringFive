package com.aaa.sb.service;

import com.aaa.sb.dao.SealDao;
import com.aaa.sb.entity.PersonsAccountNumberState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * className:SealServiceImpl
 * discription:
 * author:Dbailing
 * createTime:2018-12-19 21:08
 */
@Service
public class SealServiceImpl implements SealService {
    @Autowired
    private SealDao sealDao;

    @Override
    public List<PersonsAccountNumberState> SealedPage(Map map) {
        List<Map> page = sealDao.SealedPage(map);
        List<PersonsAccountNumberState> userList = new ArrayList<>(); //自定义一个集合把分页查询的值放入  把状态转为中文
        if(page!=null&page.size()>0) {        //把状态的值转会为中文 传到前台
            PersonsAccountNumberState state = null;
            String pState = "";  //个人状态
            String uRatio = "";  //单位缴存比例
            String pRatio = "";  //个人缴存比例
            for (int i = 0; i < page.size(); i++) {
                Map maps = page.get(i);
                //转换状态信息
                if(maps.get("PERACCSTATE").equals(1)){
                    pState="正常";
                }else if(maps.get("PERACCSTATE").equals(2)){
                    pState="封存";
                }else{
                    pState="销户";
                }
                uRatio = maps.get("UBITNROP")+"%";
                pRatio = maps.get("INDINROP")+"%";
                state = new PersonsAccountNumberState(maps.get("GRZH")+"",maps.get("PNAME")+"",uRatio,pRatio,Double.parseDouble(maps.get("BASENUMMBER")+""),Double.parseDouble(maps.get("DALANCE")+""),pState,maps.get("LASTNAYDATE")+"");
                userList.add(state);
            }
        }
        return userList;
    }
}
