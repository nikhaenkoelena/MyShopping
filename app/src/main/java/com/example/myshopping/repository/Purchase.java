package com.example.myshopping.repository;

public class Purchase {

    private int uniqId;
    private String text;
    private String time;
    private Boolean isBought;

    public Purchase(int uniqId, String text, String time, Boolean isBought) {
        this.uniqId = uniqId;
        this.text = text;
        this.time = time;
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
}
