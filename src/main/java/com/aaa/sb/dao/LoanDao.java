package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * className:LoanDao
 * discription:
 * author:MVP
 * createTime:2018-12-10 10:43
 */
public interface LoanDao {
    /**
     * 根据账号获取贷款申请客户列表
     * @param client
     * @return
     */
    @Select(value ="select GRZH,a.PID,PNAME,PSEX,PHONE,IDCARD,IDNUMBER,PMARRIAGE,PROFESSION,EDUCATION,PEMAIL,PADDRESS,\n" +
            "                             UNIT_ID,to_char(LASTNAYDATE,'yyyy-MM-dd') as LASTNAYDATE, PERACCSTATE,DALANCE,UNAME,UOPIPHONE from\n" +
            "                            TB_PERSON_INFO p left join TB_PACCOUNTUTIL a on a.pid=p.pid left \n" +
            "                            join TB_UNIT u on u.UNID=a.unid where a.grzh=#{GRZH}\n" +
            "                            ")
    Map getList(String GRZH);

    /**
     *-
     * 添加贷款信息
     *
     * @return
     */
    /*@Insert("insert into tb_loannappval(loan_id,pid,GRZH,SALARY,bank_money,loan_money,loan_periods,loan_rate,loan_bank,loan_repay,"+
            "loan_repayer,loan_repaydate,REPAY_BANK,REPAY_ACCOUNTNAME,REPAY_ACCOUNT,STATUS,CTIME,HOUSE_TYPE,HOUSE_LOCATION," +
            "house_area,BUY_ID,buy_name,bank_account,house_total,house_first," +
            "house_price,pawn_type,pawn_name,pawn_idnumber,pawn_address,pawn_status,pawn_money) " +
            "values(LOANAPPVAL .nextval,#{TB_PID},#{GRZH},#{SALARY},#{BANK_MONEY},#{LOAN_MONEY}," +
            "#{LOAN_PERIODS},#{LOAN_RATE},#{LOAN_BANK},#{LOAN_REPAY},#{LOAN_REPAYER},#{LOAN_REPAYDATE},#{REPAY_BANK},#{REPAY_ACCOUNTNAME},#{REPAY_ACCOUNT},1," +
            "#{CTIME},#{HOUSE_TYPE},#{HOUSE_LOCATION},#{HOUSE_AREA},#{BUY_ID},#{BUY_NAME},#{BNAK_ACCOUNT},#{HOUSE_TOTAL},#{HOUSE_FIRST}," +
            "#{HOUSE_PRICE},#{PAWN_TYPE},#{PAWN_NAME},#{PAWN_IDNUMBER},#{PAWN_ADDRESS},#{PAWN_STATUS},#{PAWN_MONEY})")*/
    @Insert("insert into tb_loanappval(loan_id,pid,grzh,togetherzh,SALARY,bank_money,loan_money,loan_periods,loan_rate,loan_bank,loan_repay,"+
            "loan_repayer,loan_repaydate,REPAY_BANK,REPAY_ACCOUNTNAME,REPAY_ACCOUNT,status,house_type,house_location," +
            "house_area,buy_id,buy_name,bank_account,house_total,house_firstpay," +
            "house_price,pawn_type,pawn_name,pawn_idnumber,pawn_address,pawn_status,pawn_money) " +
            "values(SEQ_LOANAPPVAL1.nextval,#{PID},#{GRZH},#{GRZH1},#{ SALARY},#{BANK_MONEY},#{loanAmount}," +
            "#{loanPeriods},#{loanRate},#{trustBank},#{paymentMethod},#{payee},#{paymentDay},#{openBank},#{paymentName},#{paymentAccount},1," +
            "#{houseType},#{houseAddress},#{houseArea},#{HIdNum},#{HName},#{HBank},#{houseTotal},#{houseFirst}," +
            "#{housePrice},#{DYType},#{DYName},#{DYIdNum},#{DYAddress},#{DYStatus},#{DYMoney})")

    int addLoan(Map map);



}
