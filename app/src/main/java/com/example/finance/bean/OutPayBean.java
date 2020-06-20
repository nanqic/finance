package com.example.finance.bean;

import java.io.Serializable;

public class OutPayBean implements Serializable {
    private int id;
    private double money;
    private String time;
    private String payee;
    private String remark;

    public OutPayBean(int id, double money, String time, String type, String payee, String remark) {
        this.id = id;
        this.money = money;
        this.time = time;
        this.type = type;
        this.payee = payee;
        this.remark = remark;
    }

    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getRemark() { return remark;    }
    public void setRemark() { this.remark = remark; }
}
