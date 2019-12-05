package com.forwawrd.domain;

import java.io.Serializable;

public class MedicineInformation implements Serializable {
    private String name;
    private Float unit_price;
    private Integer amount;
    private String write_time;

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

    public String getWrite_time() {
        return write_time;
    }

    public void setWrite_time(String write_time) {
        this.write_time = write_time;
    }

    @Override
    public String toString() {
        return "MedicineInformation{" +
                "name='" + name + '\'' +
                ", unit_price=" + unit_price +
                ", amount=" + amount +
                ", write_time='" + write_time + '\'' +
                '}';
    }
}
