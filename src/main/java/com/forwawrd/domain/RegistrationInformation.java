package com.forwawrd.domain;

import java.io.Serializable;
import java.util.Date;

public class RegistrationInformation implements Serializable {
    private Integer ID;
    private Integer medical_history_number;
    private String name;
    private Integer gender;
    private String ID_card_number;
    private Date birthday;
    private Integer age;
    private String age_type;
    private String home_address;
    private Integer registration_doctor;
    private Date registration_time;
    private Integer need_medical_history_booklet;
    private Integer registor;
    private Integer statement;
    private String registration_onlyDay;
    private String registration_onlyTime;
    private String office;


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getMedical_history_number() {
        return medical_history_number;
    }

    public void setMedical_history_number(Integer medical_history_number) {
        this.medical_history_number = medical_history_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getID_card_number() {
        return ID_card_number;
    }

    public void setID_card_number(String ID_card_number) {
        this.ID_card_number = ID_card_number;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAge_type() {
        return age_type;
    }

    public void setAge_type(String age_type) {
        this.age_type = age_type;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public Integer getRegistration_doctor() {
        return registration_doctor;
    }

    public void setRegistration_doctor(Integer registration_doctor) {
        this.registration_doctor = registration_doctor;
    }

    public Date getRegistration_time() {
        return registration_time;
    }

    public void setRegistration_time(Date registration_time) {
        this.registration_time = registration_time;
    }

    public Integer getNeed_medical_history_booklet() {
        return need_medical_history_booklet;
    }

    public void setNeed_medical_history_booklet(Integer need_medical_history_booklet) {
        this.need_medical_history_booklet = need_medical_history_booklet;
    }

    public Integer getRegistor() {
        return registor;
    }

    public void setRegistor(Integer registor) {
        this.registor = registor;
    }

    public Integer getStatement() {
        return statement;
    }

    public void setStatement(Integer statement) {
        this.statement = statement;
    }

    public String getRegistration_onlyDay() {
        return registration_onlyDay;
    }

    public void setRegistration_onlyDay(String registration_onlyDay) {
        this.registration_onlyDay = registration_onlyDay;
    }

    public String getRegistration_onlyTime() {
        return registration_onlyTime;
    }

    public void setRegistration_onlyTime(String registration_onlyTime) {
        this.registration_onlyTime = registration_onlyTime;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    @Override
    public String toString() {
        return "RegistrationInformation{" +
                "ID=" + ID +
                ", medical_history_number=" + medical_history_number +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", ID_card_number='" + ID_card_number + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", age_type='" + age_type + '\'' +
                ", home_address='" + home_address + '\'' +
                ", registration_doctor=" + registration_doctor +
                ", registration_time=" + registration_time +
                ", need_medical_history_booklet=" + need_medical_history_booklet +
                ", registor=" + registor +
                ", statement=" + statement +
                ", registration_onlyDay='" + registration_onlyDay + '\'' +
                ", registration_onlyTime='" + registration_onlyTime + '\'' +
                ", office='" + office + '\'' +
                '}';
    }
}
