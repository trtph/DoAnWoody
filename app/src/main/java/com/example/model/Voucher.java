package com.example.model;

public class Voucher {
    private String NameVoucher;
    private String ContentVoucher;
    private int ImageVoucher;

    public String getNameVoucher() {
        return NameVoucher;
    }

    public void setNameVoucher(String nameVoucher) {
        NameVoucher = nameVoucher;
    }

    public String getContentVoucher() {
        return ContentVoucher;
    }

    public void setContentVoucher(String contentVoucher) {
        ContentVoucher = contentVoucher;
    }

    public int getImageVoucher() {
        return ImageVoucher;
    }

    public void setImageVoucher(int imageVoucher) {
        ImageVoucher = imageVoucher;
    }

    public Voucher(String nameVoucher, String contentVoucher, int imageVoucher) {
        NameVoucher = nameVoucher;
        ContentVoucher = contentVoucher;
        ImageVoucher = imageVoucher;
    }
}
