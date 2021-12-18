package com.example.model;

public class productshopModel {
    private int productThumb;
    private String productName;
    private double productPrice;
    private int numberInCart;


    public productshopModel(int productThumb, String productName, double productPrice) {
        this.productThumb = productThumb;
        this.productName = productName;
        this.productPrice = productPrice;
        this.numberInCart = numberInCart;

    }

    public int getNumberInCart() { return numberInCart; }

    public void setNumberInCart(int numberInCart) { this.numberInCart = numberInCart; }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductThumb(int productThumb) {
        this.productThumb = productThumb;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public int getProductThumb() {
        return productThumb;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return "productshopModel{" +
                "productThumb=" + productThumb +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}

