package com.example.model;

public class productshopModel {
    private int productThumb;
    private String productName;
    private double productPrice;

    public productshopModel(int productThumb, String productName, double productPrice) {
        this.productThumb = productThumb;
        this.productName = productName;
        this.productPrice = productPrice;
    }

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

