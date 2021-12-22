package com.example.utils;

import java.io.Serializable;

public class AppUtils implements Serializable {

    private String card;

    public AppUtils(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
}
