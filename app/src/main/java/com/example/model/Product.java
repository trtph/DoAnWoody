package com.example.model;

public class Product {
    private int productThumb;
    private String productName;
    private double productNumber;

    public Product(int productThumb, String productName, double productNumber) {
        this.productThumb = productThumb;
        this.productName = productName;
        this.productNumber = productNumber;
    }

    public int getProductThumb() {
        return productThumb;
    }

    public void setProductThumb(int productThumb) {
        this.productThumb = productThumb;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(double productNumber) {
        this.productNumber = productNumber;
    }
}
