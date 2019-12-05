package com.forwawrd.domain;

public class patient {
    private Integer medical_history_number;
    private String name;
    private Integer ID;

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

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "patient{" +
                "medical_history_number=" + medical_history_number +
                ", name='" + name + '\'' +
                ", ID=" + ID +
                '}';
    }
}
