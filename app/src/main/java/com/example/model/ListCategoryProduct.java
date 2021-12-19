package com.example.model;

public class ListCategoryProduct {
    private int productThumb1;
    private String productName1;


    public ListCategoryProduct(int productThumb1, String productName1) {
        this.productThumb1 = productThumb1;
        this.productName1 = productName1;

    }

    public void setProductName1(String productName1) {
        this.productName1 = productName1;
    }

    public void setProductThumb1(int productThumb1) {
        this.productThumb1 = productThumb1;
    }


    public int getProductThumb1() {
        return productThumb1;
    }

    public String getProductName1() {
        return productName1;
    }

    @Override
    public String toString() {
        return "ListCategoryProduct{" +
                "productThumb1=" + productThumb1 +
                ", productName1='" + productName1 + '\'' +
                '}';
    }
}