package com.example.model;

public class FavouriteProduct {

    private String prThumb;
    private String prName;
    private String prPrice;

    public FavouriteProduct(String prThumb, String prName, String prPrice) {
        this.prThumb = prThumb;
        this.prName = prName;
        this.prPrice = prPrice;
    }

    public FavouriteProduct() {
    }

    public String getPrThumb() {
        return prThumb;
    }

    public void setPrThumb(String prThumb) {
        this.prThumb = prThumb;
    }

    public String getPrName() {
        return prName;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public String getPrPrice() {
        return prPrice;
    }

    public void setPrPrice(String prPrice) {
        this.prPrice = prPrice;
    }
}
