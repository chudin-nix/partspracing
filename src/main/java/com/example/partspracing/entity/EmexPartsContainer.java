package com.example.partspracing.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "Details")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmexPartsContainer {
    @XmlElement(name = "SoapDetailItem")
    private List<EmexPartDto> parts;

    public List<EmexPartDto> getParts() {
        return parts;
    }

    public void setParts(List<EmexPartDto> parts) {
        this.parts = parts;
    }
}