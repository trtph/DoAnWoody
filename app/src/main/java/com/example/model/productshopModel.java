package com.example.model;

public class productshopModel {
    private String prThumb;
    private String prName;
    private String prPrice;
    private String totalQuantity;
    int totalPrice;
    String documentID;

    public productshopModel() {
    }

    public productshopModel(String prThumb, String prName, String prPrice, String totalQuantity, int totalPrice) {
        this.prThumb = prThumb;
        this.prName = prName;
        this.prPrice = prPrice;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
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

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}




