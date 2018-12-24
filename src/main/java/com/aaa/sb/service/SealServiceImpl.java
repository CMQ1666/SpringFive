package com.aaa.sb.service;

import com.aaa.sb.dao.SealDao;
import com.aaa.sb.entity.PersonsAccountNumberState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public List<Map> verification(Map map) {
        return sealDao.verification(map);
    }

    @Override
    public List<Map> loansVerification(Map map) {
        return sealDao.loansVerification(map);
    }

    @Override
    public List<Map> getPage(Map map) {
        return sealDao.getPage(map);
    }

    @Override
    public Map unsealAudit(Map map, HttpSession session) {
        String name = session.getAttribute("name")+"";   //获取操作人员

        List<Map> unsealAudit = sealDao.unsealAudit(map);
        String pdstype = map.get("pdstype")+"";
        System.out.println("==========="+pdstype);
        String pState=" ";
        if(pdstype.equals("封存")){          //把文字 转成 数字 存入审核表
            pState="2";
        }else if(pdstype.equals("销户")){
            pState="0";
        }else if(pdstype.equals("启封")){
            pState="1";
        }

        Map maps = new HashMap();
        if(unsealAudit!=null&&unsealAudit.size()>0){
            for (Map tempMap : unsealAudit) {
                maps.put("UNSEAL_NAME", tempMap.get("PNAME"));		//（启封/销户）人姓名
                maps.put("UNSEAL_UNIT", tempMap.get("UNAME"));			//所在单位
                maps.put("UNSEAL_SEX", tempMap.get("PSEX"));		//性别
                maps.put("UNIT_POST", tempMap.get("PROFESSION"));//工作职位
                maps.put("UNSEAL_PHONE", tempMap.get("PHONE"));	//手机号
                maps.put("UNSEAL_NUMBER", tempMap.get("IDNUMBER"));//身份证号
                maps.put("UNSEAL_ACCOUNT", tempMap.get("GRZH"));				//销户人个人账户
                maps.put("REASON", map.get("resson"));				//启封 或 销户 原因
                maps.put("OPERATOR",name);							//操作人
                maps.put(" AUDIT_STATE",map.get("pdstype"));			//审核状态
                maps.put("STATE",pState);
            }
            System.out.println(maps+"ereijtowejtiweoi");
        }
        return maps;
    }

    @Override
    public int unsealAuditAdd(Map map) {
        return sealDao.unsealAuditAdd(map);
    }

    @Override
    public List<Map> sealAudit(Map map) {
        return sealDao.sealAudit(map);
    }

    @Override
    public Map operationQuery(Map map) {
        List<Map> operationMap = sealDao.operationQuery(map);
        Map maps = new HashMap();
        if (operationMap.size()>0&&operationMap!=null){
            String pState = "";  //个人状态
            for (Map tempMap : operationMap) {
                maps.put("DWZH", tempMap.get("DWZH"));	//单位账号
                maps.put("PNAME", tempMap.get("PNAME")); //个人名称
                maps.put("UNAME", tempMap.get("UNAME")); //单位名称
                maps.put("GRZH", tempMap.get("GRZH")); //个人账号
                if(tempMap.get("PERACCSTATE").equals(1)){  //判断状态  修改成汉字
                    pState="正常";
                }else if(tempMap.get("PERACCSTATE").equals(2)){
                    pState="封存";
                }else{
                    pState="销户";
                }
                maps.put("PERACCSTATE",pState);
            }
            return maps;
        }else {
            return null;
        }
    }

    @Override
    public List<Map> SealedPage(Map map) {
       return sealDao.SealedPage(map);
    }
}
