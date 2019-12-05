package com.forwawrd.domain;

import java.io.Serializable;

public class User implements Serializable {
    private Integer ID;
    private String user_name;
    private String password;
    private String realname;
    private Integer office;
    private String title;
    private Integer registration_level;
    private Integer delete_flag;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getOffice() {
        return office;
    }

    public void setOffice(Integer office) {
        this.office = office;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRegistration_level() {
        return registration_level;
    }

    public void setRegistration_level(Integer registration_level) {
        this.registration_level = registration_level;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", office=" + office +
                ", title='" + title + '\'' +
                ", registration_level=" + registration_level +
                ", delete_flag=" + delete_flag +
                '}';
    }
}
