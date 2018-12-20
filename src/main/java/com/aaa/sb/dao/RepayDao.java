package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:RepayDao
 * discription:
 * author:MVP
 * createTime:2018-12-15 11:05
 */
public interface RepayDao {
    /**
     * 贷款信息
     * @param map
     * @return
     */
        /*@Select("<script> select a.pname,a.grzh,a.loan_money,loan_periods,repayed_money,residue_periods,residue_money from tb_repay a  \n" +
                "<where><if test=\"pname!=null and pname!=''\"> and pname like '%'||#{pname}||'%'</if></where></script>")*/

        @Select("<script>select l.grzh,a.pname,l.loan_money,l.loan_periods,\n" +
                "r.repayed_money,r.residue_periods,r.residue_money\n" +
                " from tb_loanappval l left join  tb_paccountutil p  on l.pid=p.pid\n" +
                " left join tb_person_info a on a.pid=p.pid  left join tb_repay r on r.loan_id=l.loan_id" +
                " <where><if test=\"PNAME!=null and PNAME!=''\"> and a. PNAME like '%'||#{PNAME}||'%'</if></where></script>")
    List<Map> getList(Map map);

    /**
     * 查询个人的贷款信息
     * @param map
     * @return
     */
    @Select("<script>\n" +
            "select l.grzh, a.pname,l.loan_money,l.loan_periods,l.loan_rate,r.residue_periods,r.residue_money from \n" +
            "tb_repay r left join tb_loanappval l on l.pid=r.pid left join tb_person_info a on a.pid=l.pid </script>")
    List<Map> getListByName(Map map);

}
