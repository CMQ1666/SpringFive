package com.aaa.sb.dao;

import org.apache.ibatis.annotations.Select;

import java.util.Map;
import java.util.List;

public interface Detaildao {
    @Select("select c.UAOWEMONERY,a.ZCK,a.DWZH,b.UNAME,a.UBDACCRUAL,a.UBDPOPULATION,a.UBDDETAILTYPE,a.HJYS,a.UBDPAYWAY,a.UBDSETTLESTATES,a.UBDCREATER,a.UBDCREATEDATE  \n" +
            "FROM TB_UNITBIZDETAIL a left join TB_UNITACCOUNT c on c.uaid=a.uaid left join TB_UNIT b on b.unid=c.unid  ")
    List<Map> getList();
}
