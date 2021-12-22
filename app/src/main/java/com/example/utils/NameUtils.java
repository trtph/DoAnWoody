package com.example.utils;

import java.io.Serializable;

public class NameUtils implements Serializable {

    private String name;

    public NameUtils(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
