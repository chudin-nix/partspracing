package com.example.partspracing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForumAutoPartDto {
    private String gid;
    private String brand;
    private String art;
    private String name;
    private int dDeliv;
    private int hDeliv;
    private int kr;
    private int num;
    private double price;
    private String whse;
    private int isReturnable;

    public String getGid() {
        return gid;
    }

    public String getBrand() {
        return brand;
    }

    public String getArt() {
        return art;
    }

    public String getName() {
        return name;
    }

    public int getdDeliv() {
        return dDeliv;
    }

    public int gethDeliv() {
        return hDeliv;
    }

    public int getKr() {
        return kr;
    }

    public int getNum() {
        return num;
    }

    public double getPrice() {
        return price;
    }

    public String getWhse() {
        return whse;
    }

    public int getIsReturnable() {
        return isReturnable;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setArt(String art) {
        this.art = art;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setdDeliv(int dDeliv) {
        this.dDeliv = dDeliv;
    }

    public void sethDeliv(int hDeliv) {
        this.hDeliv = hDeliv;
    }

    public void setKr(int kr) {
        this.kr = kr;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWhse(String whse) {
        this.whse = whse;
    }

    public void setIsReturnable(int isReturnable) {
        this.isReturnable = isReturnable;
    }
}
