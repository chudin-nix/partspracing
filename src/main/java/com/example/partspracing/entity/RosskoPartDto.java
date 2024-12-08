package com.example.partspracing.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class RosskoPartDto{
    @XmlElement(name = "guid")
    private String guid;
    @XmlElement(name = "brand")
    private String brand;

    @XmlElement(name = "partnumber")
    private String partNumber;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "id")
    private String id;

    @XmlElement(name = "price")
    private double price;

    @XmlElement(name = "count")
    private int count;

    @XmlElement(name = "multiplicity")
    private int multiplicity;

    @XmlElement(name = "type")
    private int type;

    @XmlElement(name = "delivery")
    private int delivery;

    @XmlElement(name = "extra")
    private int extra;

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "deliverystart")
    private String deliveryStart;
    @XmlElement(name = "stocks")
    private String[] stocks;

    public String[] getStocks() {
        return stocks;
    }

    public String getGuid() {
        return guid;
    }

    public String getBrand() {
        return brand;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public int getMultiplicity() {
        return multiplicity;
    }

    public int getType() {
        return type;
    }

    public int getDelivery() {
        return delivery;
    }

    public int getExtra() {
        return extra;
    }

    public String getDescription() {
        return description;
    }

    public String getDeliveryStart() {
        return deliveryStart;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setMultiplicity(int multiplicity) {
        this.multiplicity = multiplicity;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setDelivery(int delivery) {
        this.delivery = delivery;
    }

    public void setExtra(int extra) {
        this.extra = extra;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDeliveryStart(String deliveryStart) {
        this.deliveryStart = deliveryStart;
    }
}
