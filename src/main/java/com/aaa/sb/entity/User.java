package com.aaa.sb.entity;

/**
 * className:User
 * discription:
 * author:cmq
 * createTime:2018-12-06 21:07
 */
public class User {
    private Integer id;
    private String name;
    private String password;
    private String perms;

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
