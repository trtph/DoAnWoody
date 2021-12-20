package com.example.model;

import java.io.Serializable;

public class SaleProduct implements Serializable {
    private String prID;
    private String prImage;
    private String prName;
    private Double prPrice;
    private Double prPriceSale;
    private String prDescription;
    private Float prRating;
    private String prRvNumber;

    public SaleProduct() {
    }

    public SaleProduct(String prID, String prImage, String prName, Double prPrice, Double prPriceSale, String prDescription, Float prRating, String prRvNumber) {
        this.prID = prID;
        this.prImage = prImage;
        this.prName = prName;
        this.prPrice = prPrice;
        this.prPriceSale = prPriceSale;
        this.prDescription = prDescription;
        this.prRating = prRating;
        this.prRvNumber = prRvNumber;
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

    public Double getPrPriceSale() {
        return prPriceSale;
    }

    public void setPrPriceSale(Double prPriceSale) {
        this.prPriceSale = prPriceSale;
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
}
