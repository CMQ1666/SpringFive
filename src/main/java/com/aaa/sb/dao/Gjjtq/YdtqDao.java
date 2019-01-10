package com.aaa.sb.dao.Gjjtq;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface YdtqDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script> select a.GRZH,a.PNAME,a.LOAN_MONEY,a.LOAN_PERIODS,a.LOAN_REPAY,a.LOAN_RATE,b.RESIDUE_MONEY," +
            "b.RESIDUE_PERIODS,a.HKJE,a.YDRQ,b.RESIDUE_MONEY,a.SHIJIAN,b.RESIDUE_PERIODS,a.DKLX,a.SHZT from " +
            "TB_AGREED_TO_EXTRACT a left join tb_repay b  on a.PERACCSTATE1=b.LOAN_ID " +
            "<where><if test=\"PNAME!=null and PNAME!=''\"> and PNAME=#{PNAME}</if> </where></script>")
    List<Map> getPageByParam(Map map);


    /**
     * 更新个人信息
     * @param map
     * @return
     */
    @Update("update tb_user set ENAME = #{ENAME},JOB=#{JOB},SAL=#{SAL},COMM=#{COMM},DEPTNO=#{DEPTNO},hiredate=to_date(#{hiredate},'yyyy-mm-dd') where empno=#{EMPNO}")
    int upadte(Map map);
}
