package com.forwawrd.domain;

public class ModelDetail {
    private String name;
    private String use_method;
    private String use_amount;
    private String use_frequency;
    private Integer amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUse_method() {
        return use_method;
    }

    public void setUse_method(String use_method) {
        this.use_method = use_method;
    }

    public String getUse_amount() {
        return use_amount;
    }

    public void setUse_amount(String use_amount) {
        this.use_amount = use_amount;
    }

    public String getUse_frequency() {
        return use_frequency;
    }

    public void setUse_frequency(String use_frequency) {
        this.use_frequency = use_frequency;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ModelDetail{" +
                "name='" + name + '\'' +
                ", use_method='" + use_method + '\'' +
                ", use_amount='" + use_amount + '\'' +
                ", use_frequency='" + use_frequency + '\'' +
                ", amount=" + amount +
                '}';
    }
}
