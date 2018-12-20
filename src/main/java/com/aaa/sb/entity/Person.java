package com.aaa.sb.entity;

/**
 * className:Person
 * discription:
 * author:Dbailing
 * createTime:2018-12-18 21:00
 */

public class Person {
    private String pame;
    private String idnumber;
    private String dalance;
    private String peraccState;

    public Person(String pame, String idnumber, String dalance, String peraccState) {
        this.pame = pame;
        this.idnumber = idnumber;
        this.dalance = dalance;
        this.peraccState = peraccState;
    }

    public String getPame() {
        return pame;
    }

    public void setPame(String pame) {
        this.pame = pame;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getDalance() {
        return dalance;
    }

    public void setDalance(String dalance) {
        this.dalance = dalance;
    }

    public String getPeraccState() {
        return peraccState;
    }

    public void setPeraccState(String peraccState) {
        this.peraccState = peraccState;
    }
}
