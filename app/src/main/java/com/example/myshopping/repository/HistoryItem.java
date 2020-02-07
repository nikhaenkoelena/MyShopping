package com.example.myshopping.repository;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "historytable")
public class HistoryItem {

    @PrimaryKey(autoGenerate = true)
    private int uniqId;
    private String text;
    private String time;
    private String image;
    private String status;

    public HistoryItem(int uniqId, String text, String time, String image, String status) {
        this.uniqId = uniqId;
        this.text = text;
        this.time = time;
        this.image = image;
        this.status = status;
    }

    @Ignore
    public HistoryItem(String text, String time, String image, String status) {
        this.text = text;
        this.time = time;
        this.image = image;
        this.status = status;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
