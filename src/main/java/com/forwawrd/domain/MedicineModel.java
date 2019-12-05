package com.forwawrd.domain;

import java.io.Serializable;

public class MedicineModel implements Serializable {
    private String name;
    private Integer use_range;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUse_range() {
        return use_range;
    }

    public void setUse_range(Integer use_range) {
        this.use_range = use_range;
    }

    @Override
    public String toString() {
        return "MedicineModel{" +
                "name='" + name + '\'' +
                ", use_range=" + use_range +
                '}';
    }
}
