package com.example.partspracing.entity;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
@Builder
public class PartDto{
    private String id;
    private String name;
    private String company;
    private int count;
    private int shippingDate;
    private BigDecimal price;
    private String source;

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

    public BigDecimal getPrice() {
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}