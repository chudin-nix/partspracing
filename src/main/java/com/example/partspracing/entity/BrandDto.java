package com.example.partspracing.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class BrandDto {

    @XmlElement(name = "MakeLogo")
    private String makeLogo;

    @XmlElement(name = "MakeName")
    private String makeName;

    public String getMakeLogo() {
        return makeLogo;
    }

    public void setMakeLogo(String makeLogo) {
        this.makeLogo = makeLogo;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }
}
