package com.aaa.sb.dao.Gjjtq;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface TqhdDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script>select a.LOAN_ID,a.LOAN_MONEY,a.LOAN_PERIODS,a.CTIME,c.UNID,a.LOAN_REPAYDATE,a.LOAN_REPAY," +
            "a.LOAN_RATE,a.REPAY_BANK,a.GRZH,b.PNAME,b.PCNUMBER,b.Dalance,d.UNAME,c.PERACCSTATE,c.LASTNAYDATE from " +
            "TB_LOANAPPVAL a left join TB_AGREED_TO_EXTRACT b on a.GRZH=b.GRZh left join TB_PACCOUNTUTIL c on a.pid=c.pid " +
            "left join TB_UNIT d on d.unid=c.unid " +
            "<where><if test=\"PCNUMBER!=null and PCNUMBER!=''\"> and b.PCNUMBER like '%'||#{PCNUMBER}||'%'</if></where></script>")
    List<Map> getPageByParam(Map map);

    /**
     * 查询分页总数量
     * @param map
     * @return
     */
    @Select("<script> select count(*) from tb_user <where>" +
            "<if test=\"empNo!=null and empNo!=''\"> and empno=#{empNo}</if>" +
            "<if test=\"ename!=null and ename!=''\"> and ename like '%'||#{ename}||'%'</if>" +
            "<if test=\"job!=null and job!=''\"> and job like '%'||#{job}||'%'</if>" +
            "<if test=\"deptNo!=null and deptNo!=''\">  and deptno =#{deptNo}</if>" +
            " </where></script>")
    int getPageCount(Map map);

    /**
     * 添加还贷人员
     * @param map
     * @return
     */
    @Insert("insert into tb_user values(seq_tbuser_id.nextval,#{ENAME},#{JOB},to_date(#{hiredate},'yyyy-mm-dd'),#{SAL},#{COMM},#{DEPTNO})")
    int add(Map map);

    /**
     * 查看还贷记录
     * @param map
     * @return
     */
    @Update("update tb_user set ENAME = #{ENAME},JOB=#{JOB},SAL=#{SAL},COMM=#{COMM},DEPTNO=#{DEPTNO},hiredate=to_date(#{hiredate},'yyyy-mm-dd') where empno=#{EMPNO}")
    int upadte(Map map);

    /**
     * 删除还贷人员
     * @param empNo
     * @return
     */
    @Delete("delete from tb_user where empno=#{empNo}")
    int delete(int empNo);

    /**
     *批量删除还贷人员
     * @param _s
     * @return
     */
    @Delete("delete from tb_user where empno in(${_parameter})")
    int batchDel(String _s);

    /**
     * 查询提取还贷人员信息
     * @param map
     * @return
     */
    @Select("<script>select a.LOAN_ID,a.LOAN_MONEY,a.LOAN_PERIODS,a.CTIME,c.UNID,a.LOAN_REPAYDATE,a.LOAN_REPAY," +
            "a.LOAN_RATE,a.REPAY_BANK,a.GRZH,b.PNAME,b.PCNUMBER,b.Dalance,d.UNAME,c.PERACCSTATE,c.LASTNAYDATE from " +
            "TB_LOANAPPVAL a left join TB_AGREED_TO_EXTRACT b on a.GRZH=b.GRZh left join TB_PACCOUNTUTIL c on a.pid=c.pid " +
            "left join TB_UNIT d on d.unid=c.unid and peraccState = 1 and b.GRZH=#{GRZH}</script>")
    List<Map> getTqhd(Map map);
}
