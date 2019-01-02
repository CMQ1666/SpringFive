package com.aaa.sb.service;

import java.util.List;
import java.util.Map;

/**
 * className:FinalService
 * discription:
 * author:Dbailing
 * createTime:2018-12-24 11:46
 */

public interface FinalService {
    /**
     * 贷款信息
     * @return
     */
    List<Map> DaiKuanXinxi();
    /**
     * 还款信息
     * @return
     */
    List<Map> huanKuanXinxi();
    /**
     * 统计每月汇缴金额
     */
    List<Map> meiYueJinE();
    /**
     * 统计每月提取金额
     */
    List<Map> meiYueTiqu();

}
