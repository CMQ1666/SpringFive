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
       // System.out.println("1231231564123"+map);
        return repayDao.getList(map);
    }

    @Override
    public List<Map> yuqiList(String GRZH) {
        List<Map> maps= repayDao.yuqiList(GRZH);
        for (Map map2 : maps) {
            String grzh = map2.get("GRZH") + "";
            String pname= map2.get("PNAME")+"";
            String loan_rate=map2.get("LOAN_RATE")+"";  //取贷款利率 计算还款利息和每月还款金额
            String loan_money = map2.get("LOAN_MONEY")+"";
            String loan_periods=map2.get("LOAN_PERIODS")+"";//贷款期数
            String repayed_periods=map2.get("REPAYED_PERIODS")+"";//已还期数
            String residue_money=map2.get("RESIDUE_MONEY")+"";//剩余的本金
            int n = Integer.valueOf(loan_periods);//总期数
            double F = Double.valueOf(loan_money);//贷款总额
            double rate = Double.valueOf(loan_rate)/100;//利率
            double i = rate/12 ;//月利率
            int p = Integer.valueOf(repayed_periods);//已还期数
            double s= n-p;//剩余期数；
            Double yuqi = Double.valueOf(residue_money)*0.003;//违约金！！！！！！
            map2.put("REPAYED_PERIODS",p);
            map2.put("RESIDUE_PERIODS",s);

            Map<Integer, BigDecimal> mapPrincipal =AverageCapitalPlusInterestUtils.getPerMonthPrincipal(F, rate, n);
            map2.put("REPAY_MONEY", mapPrincipal.get(p+1));//月应还本金
            Map<Integer, BigDecimal> mapInterest = AverageCapitalPlusInterestUtils.getPerMonthInterest(F, rate,n );
            //月应还利息
            Double  yq=yuqi+Double.valueOf(mapInterest.get(p+1)+"");
            map2.put("REPAY_MONTH_INTERRSTS",yq);
            // System.out.println(mapInterest.get(p+1));
            double  repayedmoney=0;
            for(int x=0;x<=p;x++) {
                // Integer  repayedmoney=0;
                repayedmoney =  repayedmoney + Double.valueOf(mapPrincipal.get(x+1)+"");
            }

            map2.put("REPAYED_MONEY",repayedmoney);//已还本金
            map2.put("RESIDUE_MONEY",F - repayedmoney);//未还本金

            double perMonthPrincipalInterest = AverageCapitalPlusInterestUtils.getPerMonthPrincipalInterest(F, rate, n);
            map2.put("REPAY_MONTH",perMonthPrincipalInterest+yuqi);//月还款
            double principalInterestCount = AverageCapitalPlusInterestUtils.getPrincipalInterestCount(F, rate, n);//应还总额
            map2.put("RESIDUE_ALL_MONEY",principalInterestCount-(p*perMonthPrincipalInterest));
            map2.put("All_MONEY",principalInterestCount);

            repayDao.archiveRepay1(map2);



        }


        return maps;

    }

    /**
     * 逾期还款计算
     * @param map
     * @return
     */
    @Override
    public List<Map> yuqi(Map map) {
        //repayDao.archiveRepay(map);

        return repayDao.yuqi(map);
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

        repayDao.insertRecord(map);
        String time = map.get("REPAYED_DATE")+"";//获取放贷日期
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
        Double invest=Double.valueOf(map.get("LOAN_MONEY")+"");
        Double y=Double.valueOf(map.get("LOAN_RATE")+"");//年利率
        Double i =y/12;//月利率
        Double loan_money = Double.valueOf(map.get("LOAN_MONEY")+"") ;                //贷款总额
        Double repay_month_money = Double.valueOf(map.get("REPAY_MONEY")+"") ;  //月还本金
        Double repay_month = Double.valueOf(map.get("REPAY_MONTH")+"") ;
        Double repay_month_interest = Double.valueOf( map.get("REPAY_MONTH_INTERRSTS")+"");//yue还利息
        Double repayed_period = Double.valueOf(map.get("REPAYED_PERIODS")+"") ;       //已还期数
        Double loan_periods = Double.valueOf(map.get("LOAN_PERIODS")+"");             //贷款期数
        String status ="1";
        map.put("REPAYED_PERIODS",repayed_period+1);
        map.put("RESIDUE_PERIODS",loan_periods-(repayed_period+1));
        map.put("REPAYED_ALL_MONEY",repay_month* (repayed_period+1));
        archiveRepay1(map);


        return 1;
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
        return repayDao.insertRecord(map);
    }

    @Override
    public List<Map> tiqian(Map map) {
        System.out.println("/*/*-/*-/*-/*-/*-/*-"+map);
        Double ss  = Double.valueOf(map.get("RESIDUE_ALL_MONEY")+"");//
        Double all= Double.valueOf(map.get("All_MONEY")+"");//
        Double grzh = Double.valueOf(map.get("GRZH")+"");//个人账户
       // Double lx = Double.valueOf(map.get("RESIDUE_INTERESTS")+"");//剩余利息
        Double repymoney = Double.valueOf(map.get("RESIDUE_MONEY")+"");//剩余本金
        Double repayedall=Double.valueOf(map.get("REPAYED_ALL_MONEY")+"");//已还全部金额
        Double repymoney1 = Double.valueOf(map.get("LOAN_MONEY")+"");//贷款金额
        Integer djq = Integer.valueOf(map.get("REPAYED_PERIODS")+"");//第几期
        double loanrate =Double.valueOf(map.get("LOAN_RATE")+"");//年利率
        Integer repnperiod3 = Integer.valueOf(map.get("LOAN_PERIODS")+"");//;贷款期数
        map.put("REPAY_MONTH_INTERRSTS",ss-repymoney);
        map.put("RESIDUE_PERIODS",0);
        map.put("REPAY_MONEY",repymoney);
        map.put("RESIDUE_MONEY",0);
        map.put("RESIDUE_INTERESTS",0);
        map.put("RESIDUE_ALL_MONEY",0);
        map.put("REPAY_MONTH",ss);
        map.put("REPAYED_MONEY",repymoney1);//提前还款后已还的本金
        map.put("REPAYED_PERIODS",djq);
        map.put("REPAYED_ALL_MONEY",repayedall+ss);
        repayDao.insertRecord(map);
        repayDao.archiveRepay1(map);
        return repayDao.tiqian(map);
    }

    /**
     * 查询个人贷款信息
     * @param
     * @return
     */
    @Override
    public List<Map> getListByName(String GRZH) {

        List<Map> maps= repayDao.getListByName(GRZH);
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
            Map<Integer, BigDecimal> mapInterest = AverageCapitalPlusInterestUtils.getPerMonthInterest(F, rate,n );
            map2.put("REPAY_MONTH_INTERRSTS",mapInterest.get(p+1)); //月应还利息
            double  repayedmoney=0;
            for(int x=0;x<=p;x++) {
                repayedmoney =  repayedmoney + Double.valueOf(mapPrincipal.get(x+1)+"");
            }
            map2.put("REPAYED_MONEY",repayedmoney);//已还本金
            map2.put("RESIDUE_MONEY",F - repayedmoney);//未还本金
            double perMonthPrincipalInterest = AverageCapitalPlusInterestUtils.getPerMonthPrincipalInterest(F, rate, n);
            map2.put("REPAY_MONTH",perMonthPrincipalInterest);//月还款
            double principalInterestCount = AverageCapitalPlusInterestUtils.getPrincipalInterestCount(F, rate, n);//应还总额
            map2.put("RESIDUE_ALL_MONEY",principalInterestCount-(p*perMonthPrincipalInterest));
            map2.put("All_MONEY",principalInterestCount);


            repayDao.archiveRepay1(map2);

        }


        return maps;
    }


}



