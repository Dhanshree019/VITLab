package com.example.vitlab;

public class Timeslot {
    String btn1;
    String btn2;
    String btn3;
    String btn4;
    String btn5;
    String btn6;
    String tvlab;
    String email;

    public Timeslot() {
    }

    public Timeslot(String btn1, String btn2, String btn3, String btn4, String btn5, String btn6, String tvlab,String email) {
        this.btn1 = btn1;
        this.btn2 = btn2;
        this.btn3 = btn3;
        this.btn4 = btn4;
        this.btn5 = btn5;
        this.btn6 = btn6;
        this.tvlab = tvlab;
        this.email=email;
    }

    public String getBtn1() {
        return btn1;
    }

    public void setBtn1(String btn1) {
        this.btn1 = btn1;
    }

    public String getBtn2() {
        return btn2;
    }

    public void setBtn2(String btn2) {
        this.btn2 = btn2;
    }

    public String getBtn3() {
        return btn3;
    }

    public void setBtn3(String btn3) {
        this.btn3 = btn3;
    }

    public String getBtn4() {
        return btn4;
    }

    public void setBtn4(String btn4) {
        this.btn4 = btn4;
    }

    public String getBtn5() {
        return btn5;
    }

    public void setBtn5(String btn5) {
        this.btn5 = btn5;
    }

    public String getBtn6() {
        return btn6;
    }

    public void setBtn6(String btn6) {
        this.btn6 = btn6;
    }

    public String getTvlab() {
        return tvlab;
    }

    public void setTvlab(String tvlab) {
        this.tvlab = tvlab;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


