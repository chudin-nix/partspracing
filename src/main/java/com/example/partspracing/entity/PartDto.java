package com.example.partspracing.entity;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class PartDto{
    private String id;
    private String name;
    private String company;
    private int count;
    private int shippingDate;
    private String price;
    private String source;

    public PartDto() {
    }

    public PartDto(String id, String name, String company, int count, int shippingDate, String price, String source) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.count = count;
        this.shippingDate = shippingDate;
        this.price = price;
        this.source = source;
    }

    public String getSource() {
        return source;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public int getCount() {
        return count;
    }

    public int getShippingDate() {
        return shippingDate;
    }

    public String getPrice() {
        return price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setShippingDate(int shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSource(String source) {
        this.source = source;
    }
}