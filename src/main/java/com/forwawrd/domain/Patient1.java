package com.forwawrd.domain;

public class Patient1 {
    private String name;
    private String ID_card_number;
    private String home_address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID_card_number() {
        return ID_card_number;
    }

    public void setID_card_number(String ID_card_number) {
        this.ID_card_number = ID_card_number;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    @Override
    public String toString() {
        return "Patient1{" +
                "name='" + name + '\'' +
                ", ID_card_number='" + ID_card_number + '\'' +
                ", home_address='" + home_address + '\'' +
                '}';
    }
}
