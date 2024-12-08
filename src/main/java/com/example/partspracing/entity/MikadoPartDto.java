package com.example.partspracing.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.math.BigDecimal;
@XmlAccessorType(XmlAccessType.FIELD)
public class MikadoPartDto{
    @XmlElement(name = "PartArticle")
    String partArticle;
    @XmlElement(name = "PartName")
    String partName;
    @XmlElement(name = "PartCompany")
    String partCompany;
    @XmlElement(name = "PartCount")
    int partCount;
    @XmlElement(name = "ShippingDate")
    int shippingDate;
    @XmlElement(name = "Price")
    BigDecimal price;

    public String getPartArticle() {
        return partArticle;
    }

    public String getPartName() {
        return partName;
    }

    public String getPartCompany() {
        return partCompany;
    }

    public int getPartCount() {
        return partCount;
    }

    public int getShippingDate() {
        return shippingDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPartArticle(String partArticle) {
        this.partArticle = partArticle;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setPartCompany(String partCompany) {
        this.partCompany = partCompany;
    }

    public void setPartCount(int partCount) {
        this.partCount = partCount;
    }

    public void setShippingDate(int shippingDate) {
        this.shippingDate = shippingDate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
