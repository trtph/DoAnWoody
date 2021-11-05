package com.example.model;

public class UserAccount {
    private String Name;
    private int Image;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public UserAccount(String name, int image) {
        Name = name;
        Image = image;
    }
}
