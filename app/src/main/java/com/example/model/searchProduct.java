package com.example.model;

public class searchProduct {
    private int productThumb;
    private String productName;


    public searchProduct(int productThumb, String productName) {
        this.productThumb = productThumb;
        this.productName = productName;

    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductThumb(int productThumb) {
        this.productThumb = productThumb;
    }





    public int getProductThumb() {
        return productThumb;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return "searchProduct{" +
                "productThumb=" + productThumb +
                ", productName='" + productName + '\'' +
                '}';
    }
}
