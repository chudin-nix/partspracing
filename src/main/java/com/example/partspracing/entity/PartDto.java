package com.example.partspracing.entity;

import lombok.Builder;

@Builder
public class PartDto{
    private String id;
    private String name;
    private String company;
    private String count;
    private String shippingDate;
    private String price;
    private String source;
    private int priority;

    public PartDto() {
    }

    public PartDto(String id, String name, String company, String count, String shippingDate, String price, String source, int priority) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.count = count;
        this.shippingDate = shippingDate;
        this.price = price;
        this.source = source;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
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

    public String getCount() {
        return count;
    }

    public String getShippingDate() {
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

    public void setCount(String count) {
        this.count = count;
    }

    public void setShippingDate(String shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}