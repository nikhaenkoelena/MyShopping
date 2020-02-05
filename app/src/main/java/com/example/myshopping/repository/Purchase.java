package com.example.myshopping.repository;

public class Purchase {

    private int uniqId;
    private String text;
    private String time;
    private String image;
    private Boolean isBought;

    public Purchase(int uniqId, String text, String time, String image, Boolean isBought) {
        this.uniqId = uniqId;
        this.text = text;
        this.time = time;
        this.image = image;
        this.isBought = isBought;
    }

    public Purchase(String text, String time, String image, Boolean isBought) {
        this.text = text;
        this.time = time;
        this.image = image;
        this.isBought = isBought;
    }

    public int getUniqId() {
        return uniqId;
    }

    public void setUniqId(int uniqId) {
        this.uniqId = uniqId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getIsBought() {
        return isBought;
    }

    public void setIsBought(Boolean isBought) {
        this.isBought = isBought;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
