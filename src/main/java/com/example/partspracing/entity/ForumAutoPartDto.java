package com.example.partspracing.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForumAutoPartDto {
    private String gid;
    private String brand;
    private String art;
    private String name;
    private String dDeliv;
    private String hDeliv;
    private int kr;
    private String num;
    private String price;
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

    public String getdDeliv() {
        return dDeliv;
    }

    public String gethDeliv() {
        return hDeliv;
    }

    public int getKr() {
        return kr;
    }

    public String getNum() {
        return num;
    }

    public String getPrice() {
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

    public void setdDeliv(String dDeliv) {
        this.dDeliv = dDeliv;
    }

    public void sethDeliv(String hDeliv) {
        this.hDeliv = hDeliv;
    }

    public void setKr(int kr) {
        this.kr = kr;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setWhse(String whse) {
        this.whse = whse;
    }

    public void setIsReturnable(int isReturnable) {
        this.isReturnable = isReturnable;
    }
}
