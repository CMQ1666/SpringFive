package com.aaa.sb.service;

import com.aaa.sb.dao.RepayDao;
import com.aaa.sb.util.AverageCapitalPlusInterestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * className:RepayServiceImpl
 * discription:
 * author:MVP
 * createTime:2018-12-15 16:15
 */
@SuppressWarnings("all")
@Service
public class RepayServiceImpl implements RepayService{
    @Autowired
    private RepayDao repayDao;

    /**
     * 查询贷款信息
     *
     * @param map
     * @return
     */
    @Override
    public List<Map> getList(Map map) {

        return repayDao.getList(map);
    }


    /**
     * 具体的计算公式是（等额本息还款）：
     a=F*i(1+i)^n/[(1+i)^n-1]
     a:月供;
     F:贷款总额;
     i:贷款利率(月利率),月利率=年利率/12;
     n:还款月数
     总利息=月还款额*贷款月数-本金
     Math.pow(m,n)，代表m的n次方
     */
    @Override
    public int archiveRepay(Map map) {

        String time = map.get("CTIME")+"";//获取放贷日期
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("输出的值为："+map+"++++++++++++++++++++++");

        //每月应还本金
        //monthCapital = (loan_money* (loan_rate/12) * (Math.pow((1+loan_rate/12),repayed_period-1)))/(Math.pow(1+(loan_rate/12),month)-1);

        Double invest=Double.valueOf(map.get("LOAN_MONEY")+"");
        Double y=Double.valueOf(map.get("LOAN_RATE")+"");//年利率
        Double i =y/12;//月利率
        Double loan_money = Double.valueOf(map.get("LOAN_MONEY")+"") ;                //贷款总额
      //  Double rp = Double.valueOf(map.get("REPAYED_PERIODS")+"") ;          //已还期数
        Double repay_month_money = Double.valueOf(map.get("REPAY_MONEY")+"") ;  //月还本金
        Double repay_month = Double.valueOf(map.get("REPAY_MONTH")+"") ;
        Double repay_month_interest = Double.valueOf( map.get("REPAY_MONTH_INTERRSTS")+"");//yue还利息
        Double repayed_period = Double.valueOf(map.get("REPAYED_PERIODS")+"") ;       //已还期数
        //Double repay_money = Double.valueOf(map.get("REPAY_MONEY")+"") ;               //应还本金
       // Double repay_interests = Double.valueOf(map.get("REPAYED_INTERESTS")+"") ;       //应还利息
        Double loan_periods = Double.valueOf(map.get("LOAN_PERIODS")+"");             //贷款期数
       // Double repayed_money = Double.valueOf(map.get("REPAYED_MONEY")+"") ;          //已还本金
       // Double repayed_interests = Double.valueOf(map.get("REPAYED_INTERESTS")+"") ;//已还利息
        // int repay_month = (int) map.get("repay_month");
        //int repay_mmonth = (int) map.get("repay_mmonth");residue_periods
        String status ="1";
        map.put("REPAYED_PERIODS",repayed_period+1);
       // map.put("RESIDUE_MONEY",loan_money-(repay_month_money*(repayed_period+1)));         //剩余本金
       // map.put("RESIDUE_INTERESTS",repay_month_interest*(loan_periods-(repayed_period+1)));//剩余利息
        //map.put("REPAYED_MONEY",repay_month_money*(repayed_period+1));                  //已还本金
        //map.put("REPAYED_INTERESTS",repay_month_interest*(repayed_period+1));               //已还利息
        map.put("RESIDUE_PERIODS",loan_periods-1);
        //已还总的金额数
        map.put("REPAYED_ALL_MONEY",repay_month* (repayed_period+1));

        System.out.println("最终的参数====="+map);

        //int state = repayDao.insertRecord(map);
        archiveRepay1(map);
        return 1;
       // return state;
    }

    @Override
    public int archiveRepay1(Map map) {

     return  repayDao.archiveRepay1(map);

    }

    /**
     * 查询个人还款记录
     * @param GRZH
     * @return
     */

    @Override
    public List<Map> getRecordByName(String GRZH) {
        return repayDao.getRecordByName(GRZH);
    }

    /**
     * 插入个人还款记录
     * @param map
     * @return
     */
    @Override
    public int insertRecord(Map map) {
       // System.out.println(map+"**************");
        return repayDao.insertRecord(map);
    }

    /**
     * 查询个人贷款信息
     * @param
     * @return
     */
    @Override
    public List<Map> getListByName(String GRZH) {
       // Map map1=new HashMap();
        List<Map> maps= repayDao.getListByName(GRZH);
        System.out.println("--------------666666************"+maps);
        for (Map map2 : maps) {
            String grzh = map2.get("GRZH") + "";
            String pname= map2.get("PNAME")+"";
            String loan_rate=map2.get("LOAN_RATE")+"";  //取贷款利率 计算还款利息和每月还款金额
            String loan_money = map2.get("LOAN_MONEY")+"";
            String loan_periods=map2.get("LOAN_PERIODS")+"";//贷款期数
            String repayed_periods=map2.get("REPAYED_PERIODS")+"";//已还期数
            int n = Integer.valueOf(loan_periods);//总期数
            double F = Double.valueOf(loan_money);//贷款总额
            double rate = Double.valueOf(loan_rate)/100;//利率
            double i = rate/12 ;//月利率
            int p = Integer.valueOf(repayed_periods);//已还期数
            double s= n-p;//剩余期数；

            map2.put("REPAYED_PERIODS",p);
            map2.put("RESIDUE_PERIODS",s);

                Map<Integer, BigDecimal> mapPrincipal =AverageCapitalPlusInterestUtils.getPerMonthPrincipal(F, rate, n);
                map2.put("REPAY_MONEY", mapPrincipal.get(p+1));//月应还本金
                System.out.println(mapPrincipal.get(p+1));
                Map<Integer, BigDecimal> mapInterest = AverageCapitalPlusInterestUtils.getPerMonthInterest(F, rate,n );
                map2.put("REPAY_MONTH_INTERRSTS",mapInterest.get(p+1)); //月应还利息
                System.out.println(mapInterest.get(p+1));

            double perMonthPrincipalInterest = AverageCapitalPlusInterestUtils.getPerMonthPrincipalInterest(F, rate, n);
            map2.put("REPAY_MONTH",perMonthPrincipalInterest);//月还款
            double principalInterestCount = AverageCapitalPlusInterestUtils.getPrincipalInterestCount(F, rate, n);//应还总额
            map2.put("RESIDUE_MONEY",principalInterestCount-(p*perMonthPrincipalInterest));
            map2.put("All_MONEY",principalInterestCount);
            repayDao.insertRecord(map2);
            repayDao.archiveRepay1(map2);


        }


        return maps;
    }


}


