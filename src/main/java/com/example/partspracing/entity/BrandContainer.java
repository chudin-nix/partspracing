package com.example.partspracing.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "GetMakesDictResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class BrandContainer {

    @XmlElement(name = "ShortMakeInfo")
    private List<BrandDto> brands;

    public List<BrandDto> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandDto> brands) {
        this.brands = brands;
    }
}
