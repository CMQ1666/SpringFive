package com.aaa.sb.dao.inspect;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * className:LoanFinallyDao
 * discription:
 * author:cmq
 * createTime:2018-12-19 20:13
 */
public interface LoanFinallyDao {
    /**
     * 获得贷款终审数据
     * @return
     */
    @Select("<script> select a.PID,a.PNAME,b.LOAN_MONEY,b.LOAN_PERIODS,b.LOAN_RATE,b.LOAN_BANK,b.LOAN_REPAY,b.CTIME,b.STATUS \n" +
            "from tb_person_info a,tb_loanappval b   \n"  +
            "<where> a.PID=b.PID and STATUS=2<if test=\"PNAME!=null and PNAME!=''\"> and a.PNAME like '%'||#{PNAME}||'%'</if></where></script>")
    List<Map> getList(Map map);

    /**
     * 信息审查
     * @param map
     * @return
     */
    @Select(value = "select a.*,b.*,c.*,d.* from tb_person_info a\n" +
            "join tb_paccountutil b on a.pid=b.pid\n" +
            "join tb_unit c on  b.unid=c.unid\n" +
            "join tb_loanappval d on a.pid=d.pid\n" +
            "where a.pid=#{PID}")
    List<Map> reList(Map map);

    /**
     * 根据共同借款人姓名查询信息
     * @param map
     * @return
     */
    @Select(value = "select a.PID,b.* from tb_paccountutil a,tb_person_info b " +
            "where a.GRZH=#{TOGETHERZH} and a.PID=b.PID")
    List<Map> rethList(Map map);

    /**
     * 终审通过
     * @return
     */
    @Update(value = "update tb_loanappval set STATUS=3 where PID=#{PID}")
    int thirdUpdate(Map map);

    /**
     * 终审驳回
     * @return
     */
    @Update(value = "update tb_loanappval set STATUS=1 where PID=#{PID}")
    int fourUpdate(Map map);

    /**
     * 终审通过
     * @param map
     * @return
     */
    @Update(value = "update tb_repay set STATE =1 where PID=#{PID}")
    int fiveUpdate(Map map);

    /**
     * 终身驳回
     * @param map
     * @return
     */
    @Update(value = "update tb_repay set STATE=2 where PID=#{PID}")
    int sixUpdate(Map map);
}
