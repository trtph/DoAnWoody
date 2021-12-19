package com.example.model;

public class ListProduct {
    private int productThumb2;
    private String productName2;
    private double productPrice2;

    public ListProduct(int productThumb2, String productName2, double productPrice2)  {
        this.productThumb2 = productThumb2;
        this.productName2 = productName2;
        this.productPrice2 = productPrice2;
    }

    public int getProductThumb2() {
        return productThumb2;
    }

    public void setProductThumb2(int productThumb2) {
        this.productThumb2 = productThumb2;
    }

    public String getProductName2() {
        return productName2;
    }

    public void setProductName2(String productName2) {
        this.productName2 = productName2;
    }

    public double getProductPrice2() {
        return productPrice2;
    }

    public void setProductPrice2(double productPrice2) {
        this.productPrice2 = productPrice2;
    }

    @Override
    public String toString() {
        return "ListProduct{" +
                "productThumb2=" + productThumb2 +
                ", productName2='" + productName2 + '\'' +
                ", productPrice2=" + productPrice2 +
                '}';
    }
}
