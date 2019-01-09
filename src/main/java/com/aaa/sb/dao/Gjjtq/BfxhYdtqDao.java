package com.aaa.sb.dao.Gjjtq;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BfxhYdtqDao {
    /**
     * 带参分页查询
     * @param map
     * @return
     * 如果使用注解的方式，动态sql必须在标签<script></script>
     * 如果使用<script></script>标签，mybatis大于小于,必须使用&gt;  &lt;
     */
    @Select("<script>select a.PACCID,a.UNID,a.OPENDATE,a.DALANCE,a.PERACCSTATE,a.BASENUMMBER,a.UBITNROP,a.INDINROP,to_char(LASTNAYDATE,'YYYY-MM-DD') as LASTNAYDATE," +
            "a.UNITMONPAYSUM,a.PERMONPAYSUM,a.YPAYAMT,a.YDRAWAMT,a.YINTERESTBAL,a.PAOP,a.REMARK,a.GRZH,a.PID,a.KHYH,a.YHZH," +
            "a.GRMM,a.CHJE,b.PNAME,b.PHONE,b.PSEX,b.IDCARD,b.IDNUMBER,b.PDATE,b.PMARRIAGE,b.PROFESSION,b.EDUCATION,b.PEMAIL," +
            "b.PADDRESS,b.UNIT_ID,b.STATE,c.UNAME,c.UADDRESS,c.ULEGALPERSON,c.ULEGALTYPE,c.ULEGALCARD,c.UPAYDATE,c.UOPERATOR," +
            "c.UOPIPHONE,c.JBRZJLX,c.JBRZJHM,c.UNETWORKCODE,c.UCREATER,c.GSLX,c.SSHY,c.LSGX,c.JJLX,c.DWXZ,c.YYZZ,c.UNITBEGINDATE," +
            "d.UAID,d.UDEPOSITRATIO,d.UPERSONRATIO,d.UDEPOSITEDPNUM,d.USEALPNUM,d.UAREMAIN,d.UACANCELLDATE,d.UACREASON,d.UASTATE,d.UAPAYENDDATE," +
            "d.UAOWEMONERY,d.UAOWEMONTHS,d.UATEBALANCE,d.UABANKNAME,d.UABANKNUMBER,d.UAWORPERSON,d.STYH,d.YWBLR,d.KHRQ,d.DWZH," +
            "e.LOAN_ID,e.SALARY,e.BANK_MONEY,e.LOAN_MONEY,e.LOAN_PERIODS,e.LOAN_RATE,e.LOAN_BANK,e.LOAN_REPAY,e.LOAN_REPAYER,e.LOAN_REPAYDATE,e.REPAY_BANK," +
            "e.REPAY_ACCOUNTNAME,e.REPAY_ACCOUNT,e.HOUSE_TYPE,e.HOUSE_LOCATION,e.HOUSE_AREA,e.BUY_NAME,e.BUY_ID,e.BANK_ACCOUNT," +
            "e.HOUSE_TOTAL,e.HOUSE_PRICE,e.PAWN_TYPE,e.PAWN_NAME,e.PAWN_IDNUMBER,e.PAWN_ADDRESS,e.PAWN_STATUS,e.PAWN_MONEY,e.STATUS,e.HOUSE_FIRSTPAY," +
            "e.CTIME,e.TOGETHERZH from tb_paccountutil a left join tb_person_info b on a.pid=b.pid left join tb_unit c on a.unid=c.unid " +
            "left join tb_loanappval e on a.pid=e.pid left join tb_unitaccount d on a.unid=d.unid " +
            "<where><if test=\"PNAME!=null and PNAME!=''\"> and b.PNAME like '%'||#{PNAME}||'%'</if></where></script>")
    List<Map> getPageByParam(Map map);

    /**
     * 公积金部分提取与销户提取
     * @param map
     * @return
     */
    @Insert("insert into TB_EXTRACT_APPLICATION(EXTRACT_APPLICATION_ID,APPLICATION_TYPE,APPLICATION_WAY,APPL_NAME,APPL_BANK_ACCOUNT," +
            "BAILOR_NAME,BILOR_IDNUM,EXTRACT_REASON,RENTING_ADDRESS,RENTING_COMPACT,RENTING_TYPE,RENTING_MONEY," +
            "HOUSE_ACREAGE,HOUSE_PART,HOUSE_ADDRESS,HOUSE_MONEY,EXTRACT_MONEY,COMP_NAME,APPL_STATE,APPL_TIME,PRE_ACCOUNT,APPL_IDNUM,APPL_BANK_NAME,COMP_ACCOUNT) " +
            "values(seq_extract_id.nextval,#{APPLICATION_TYPE},#{APPLICATION_WAY}," +
            "#{PNAME},#{YHZH},#{BAILOR_NAME},#{BILOR_IDNUM},#{EXTRACT_REASON},#{RENTING_ADDRESS},#{RENTING_COMPACT}," +
            "#{RENTING_TYPE},#{RENTING_MONEY},#{HOUSE_ACREAGE},#{HOUSE_PART},#{HOUSE_ADDRESS},#{HOUSE_MONEY},#{EXTRACT_MONEY},#{UNAME},1,SYSDATE,#{GRZH},#{IDNUMBER},#{APPL_BANK_NAME},#{DWZH})")
    int add(Map map);


    /**
     * 偿还购房贷款本息
     */
    @Insert("insert into TB_AGREED_TO_EXTRACT(BID,PNAME,PIPHONE,PCNUMBER,GRZH,DALANCE,PERACCSTATE,LOAN_MONEY,LOAN_PERIODS,LOAN_REPAY,LOAN_RATE,PERACCSTATE1,YDRQ,HKJE,YDQS,DKLX,SHZT,SHIJIAN) values(seq_agreed_id.nextval,#{PNAME},#{PHONE},#{IDNUMBER},#{GRZH},#{DALANCE},#{STATE},#{LOAN_MONEY},#{LOAN_PERIODS},'等额本息',#{LOAN_RATE},#{LOAN_ID},#{APPOINTTIME},#{REFUND_LIMIT},#{LOAN_PERIODS},#{LOANTYPE},1,to_char(sysdate,'yyyy-MM-dd'))")
    int addDaikuan(Map map);

}
