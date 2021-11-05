package com.example.model;

public class FavouriteProduct {
    private int productThumb;
    private String productName;
    private double productPrice;

    public FavouriteProduct(int productThumb, String productName, double productPrice) {
        this.productThumb = productThumb;
        this.productName = productName;
        this.productPrice = productPrice;
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "FavouriteProduct{" +
                "productThumb=" + productThumb +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
