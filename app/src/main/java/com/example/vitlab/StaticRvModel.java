package com.example.vitlab;

public class StaticRvModel {
    private int image;
    private String lab;
    private String time;
    private String incharge;
    private String lunchtime;

    public StaticRvModel() {
    }

    public StaticRvModel(int image, String lab, String time, String incharge, String lunchtime) {
        this.image = image;
        this.lab = lab;
        this.time = time;
        this.incharge = incharge;
        this.lunchtime = lunchtime;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLab() {
        return lab;
    }

    public void setLab(String lab) {
        this.lab = lab;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIncharge() {
        return incharge;
    }

    public void setIncharge(String incharge) {
        this.incharge = incharge;
    }

    public String getLunchtime() {
        return lunchtime;
    }

    public void setLunchtime(String lunchtime) {
        this.lunchtime = lunchtime;
    }
}
