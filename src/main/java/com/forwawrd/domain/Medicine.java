package com.forwawrd.domain;

import java.io.Serializable;
import java.util.Date;

public class Medicine implements Serializable {
    private Integer ID;
    private String name;
    private Float unit_price;
    private Integer amount;
    private String statement;
    private String doctor_name;
    private String prescription_name;
    private Date time;
    private String formated_time;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Float unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getFormated_time() {
        return formated_time;
    }

    public void setFormated_time(String formated_time) {
        this.formated_time = formated_time;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getPrescription_name() {
        return prescription_name;
    }

    public void setPrescription_name(String prescription_name) {
        this.prescription_name = prescription_name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", unit_price=" + unit_price +
                ", amount=" + amount +
                ", statement='" + statement + '\'' +
                ", doctor_name='" + doctor_name + '\'' +
                ", prescription_name='" + prescription_name + '\'' +
                ", time=" + time +
                ", formated_time='" + formated_time + '\'' +
                '}';
    }
}
