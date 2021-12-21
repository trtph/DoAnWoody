package com.example.model;

import java.io.Serializable;

public class AllProductModel implements Serializable {
    private String prID;
    private String prImage;
    private String prName;
    private Double prPrice;
    private String prDescription;
    private Float prRating;
    private String prRvNumber;
    private String prType;

    public AllProductModel() {
    }

    public AllProductModel(String prID, String prImage, String prName, Double prPrice, String prDescription, Float prRating, String prRvNumber, String prType) {
        this.prID = prID;
        this.prImage = prImage;
        this.prName = prName;
        this.prPrice = prPrice;
        this.prDescription = prDescription;
        this.prRating = prRating;
        this.prRvNumber = prRvNumber;
        this.prType = prType;
    }

    public String getPrID() {
        return prID;
    }

    public void setPrID(String prID) {
        this.prID = prID;
    }

    public String getPrImage() {
        return prImage;
    }

    public void setPrImage(String prImage) {
        this.prImage = prImage;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public Double getPrPrice() {
        return prPrice;
    }

    public void setPrPrice(Double prPrice) {
        this.prPrice = prPrice;
    }

    public String getPrDescription() {
        return prDescription;
    }

    public void setPrDescription(String prDescription) {
        this.prDescription = prDescription;
    }

    public Float getPrRating() {
        return prRating;
    }

    public void setPrRating(Float prRating) {
        this.prRating = prRating;
    }

    public String getPrRvNumber() {
        return prRvNumber;
    }

    public void setPrRvNumber(String prRvNumber) {
        this.prRvNumber = prRvNumber;
    }

    public String getPrType() {
        return prType;
    }

    public void setPrType(String prType) {
        this.prType = prType;
    }
}
