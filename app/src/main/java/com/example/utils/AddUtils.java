package com.example.utils;

import java.io.Serializable;

public class AddUtils implements Serializable {

    private String address;

    public AddUtils(String address) {
        this.address= address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}