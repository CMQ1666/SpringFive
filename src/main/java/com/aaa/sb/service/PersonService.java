package com.aaa.sb.service;

import com.aaa.sb.dao.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:PersonService
 * discription:
 * author:Dbailing
 * createTime:2018-12-13 14:49
 */

public interface PersonService {

    /**
     * 分页查询
     * @return
     */

    List<Map> getList(Map map);
    /**
     * 根据用户姓名查询账户单位和银行
     * @return
     */
    Map UserIDSelect(String pname);

    /**
     * 公司列表查询
     *  状态为正常的
     *  显示到提交审核处
     * @return
     */
    List<Map> getUnit();
    /**
     *下拉框选中公司 获取ID  查询公司的受托银行
     * 显示到提交审核处
     * @return
     */
    Map getUintById(Integer unid);
    /**
     * 查询待审核表  查询审核信息  前台回去的值对比 相同操作的人
     * @param map
     * @return
     */
    List<Map> submitVerify(Map map);


    /**
     * 根据id查询数据 个人信息  然后添加到待转移列表
     * @return
     */
    Map shift(Integer pid);
    /**
     * 下拉框选中公司 获取ID  查询公司   添加到审核表中
     * @return
     */
    Map uname(Integer unid);
    /**
     * 把获取到的人员信息存入审核表中
     * @return
     */
    int addShift(Map map);
    /**
     * 待转移用户列表
     * @return
     */
    List<Map> UserSelect(Map map);
    /**
     * 第一次调用  查询人员转移审核表信息         第二次调用    查询待审核表  查询审核信息  前台回去的值对比 相同操作的人
     * @return
     */
    List<Map> uditTransfer(Map map);


}
