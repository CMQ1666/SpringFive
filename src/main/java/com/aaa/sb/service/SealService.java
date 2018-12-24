package com.aaa.sb.service;

import com.aaa.sb.entity.PersonsAccountNumberState;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * className:SealService
 * discription:
 * author:Dbailing
 * createTime:2018-12-19 20:54
 */

public interface SealService {
    /**
     * 封存、启封、销户 分页
     * @param map
     * @return
     */
    List<Map> SealedPage(Map map);

    /**
     * 判断唯一性校验  封存 启封 销户 校验   不能重复操作
     * @param map
     * @return
     */
    List<Map> verification(Map map);

    /**
     * 校验贷款的人 不能销户 和 封存
     * @param map
     * @return
     */
    List<Map> loansVerification(Map map);
    /**
     * 封存 启封 销户   操作弹出层查询信息
     * @param map
     * @return
     * element--controller放到service层
     */
    Map operationQuery(Map map);
    /**
     * 获取审核信息   放入审核表中
     * @param map
     * @return
     * element 放到service层
     */
    Map unsealAudit(Map map,HttpSession session);

    /**
     * 获取审核信息  添加到审核表中
     * @param map
     * @return
     * element
     */
    int unsealAuditAdd(Map map);
    /**
     * 启封 封存审核表分页查询
     * @param map
     * @return
     */
    List<Map>  sealAudit(Map map);
    /**
     * 明细列表分页
     */
    List<Map> getPage(Map map);

}
