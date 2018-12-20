package com.aaa.sb.entity;

/**
 * className:PersonsAccountNumberState
 * discription:
 * author:Dbailing
 * createTime:2018-12-19 21:00
 */

public class PersonsAccountNumberState {
    private String grzh;        //个人账号
    private String pname; 	//账户姓名
    private String ubitnrop;	//单位缴存比例
    private String indinrop; 	//个人缴存比例
    private double basenummber; //缴存基数

    public PersonsAccountNumberState(String grzh, String pname, String ubitnrop, String indinrop, double basenummber, double dalance, String peraccstate, String lastnaydate) {
        this.grzh = grzh;
        this.pname = pname;
        this.ubitnrop = ubitnrop;
        this.indinrop = indinrop;
        this.basenummber = basenummber;
        this.dalance = dalance;
        this.peraccstate = peraccstate;
        this.lastnaydate = lastnaydate;
    }

    public String getGrzh() {
        return grzh;
    }

    public void setGrzh(String grzh) {
        this.grzh = grzh;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUbitnrop() {
        return ubitnrop;
    }

    public void setUbitnrop(String ubitnrop) {
        this.ubitnrop = ubitnrop;
    }

    public String getIndinrop() {
        return indinrop;
    }

    public void setIndinrop(String indinrop) {
        this.indinrop = indinrop;
    }

    public double getBasenummber() {
        return basenummber;
    }

    public void setBasenummber(double basenummber) {
        this.basenummber = basenummber;
    }

    public double getDalance() {
        return dalance;
    }

    public void setDalance(double dalance) {
        this.dalance = dalance;
    }

    public String getPeraccstate() {
        return peraccstate;
    }

    public void setPeraccstate(String peraccstate) {
        this.peraccstate = peraccstate;
    }

    public String getLastnaydate() {
        return lastnaydate;
    }

    public void setLastnaydate(String lastnaydate) {
        this.lastnaydate = lastnaydate;
    }

    private double dalance; 	//个人公积金余额
    private String peraccstate;
    private String lastnaydate; //最后汇缴月

}
