package com.aaa.sb.dao;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:PersonDao
 * discription:
 * author:Dbailing
 * createTime:2018-12-12 20:55
 */


public interface PersonDao {
    /**
     * 获得分页数据
     * @return
     */
    @Select("<script> select a.pname,a.idnumber,b.dalance,b.peraccState from tb_person_info a,tb_paccountutil b where a.pid=b.pid\n" +
            "<if test=\"pname!=null and pname!=''\"> and pname like '%'||#{pname}||'%'</if></script>")
    List<Map> getList(Map map);
    /**
     * 点击转移出现转出账户单位和受托银行
     * @return
     */
    @Select("select a.pname,a.pid,b.pid,b.unid,c.unid,c.uname,d.unid,d.uabankname from  tb_person_info a,tb_paccountutil b,tb_unit c,tb_unitaccount d where a.pid=b.pid and b.unid=c.unid and c.unid=d.unid and a.pname= #{pname}")
    Map UserIDSelect(String pname);
    /**
     * 公司列表查询
     *  状态为正常的
     *  显示到提交审核处
     * @return
     */
    @Select("select a.unid,a.uname from tb_unit a left join tb_unitaccount b on b.unid=a.unid  where uastate=1")
    List<Map> getUnit();
    /**
     *下拉框选中公司 获取ID  查询公司的受托银行
     * 显示到提交审核处
     * @return
     */
    @Select("select a.unid,b.unid,b.STYH from tb_unit a left join tb_unitaccount b on a.unid=b.unid where a.unid=#{UNID}")
    Map getUintById(Integer unid);
    /**
     * 查询待审核表  查询审核信息  前台回去的值对比 相同操作的人
     * @param map
     * @return
     */
    @Select("select transfer_id,pname,idNumber,balance,state,transfer_out_unit,transfer_into_unit,auditor,person_account,transfer_reason,operator,submit_time,audit_state,id from tb_transfer_audit where id = #{id}")
    List<Map> submitVerify(Map map);

    /**
     * 根据id查询数据 个人信息  然后添加到待转移列表
     * @return
     */
    @Select("select a.pname,a.idnumber,b.basenummber,a.pid,b.grzh,b.dalance,b.peraccstate from tb_person_info a ,tb_paccountutil b where a.pid=b.pid and a.pid =#{PID}")
    Map shift(Integer pid);
    /**
     * 下拉框选中公司 获取ID  查询公司   添加到审核表中
     * @return
     */
    @Select("select a.unid,b.unid,a.uname from tb_unit a,tb_unitaccount b  where a.unid=b.unid and a.unid=#{UNID}")
    Map uname(Integer unid);
    /**
     * 把获取到的人员信息存入审核表中
     * @return
     */
    @Insert("insert into tb_transfer_audit (transfer_id,pname,idnumber,balance,state,transfer_out_unit,transfer_into_unit,auditor,person_account,transfer_reason,operator,submit_time,audit_state,basenummber,id)values(seq_transfer_id.nextval,#{pname},#{idnumber},#{balance},#{state},#{transfer_out_unit},#{transfer_into_unit},#{auditor},#{person_account},#{transfer_reason},#{operator},#{submit_time},#{audit_state},#{basenummber},#{id})")
    int addShift(Map map);
    /**
     * 带转移用户列表
     * @return
     */
    @Select("<script>select pname,idnumber,balance,state from tb_transfer_audit   where 1=1" +
            "<if test=\"pname!=null and pname!=''\"> and pname like '%'||#{pname}||'%'</if></script>")
    List<Map> UserSelect(Map map);
    /**
     * 第一次调用  查询人员转移审核表信息         第二次调用    查询待审核表  查询审核信息  前台回去的值对比 相同操作的人
     * @return
     */
    @Select("<script>select transfer_id,pname,idnumber,balance,state,transfer_out_unit,transfer_into_unit,auditor,person_account,transfer_reason,operator,submit_time,audit_state,id from tb_transfer_audit where 1=1 " +
            "<if test=\"pname!=null and pname!=''\"> and pname like '%'||#{pname}||'%'</if></script>")
    List<Map> uditTransfer(Map map);

}