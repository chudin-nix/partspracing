package com.example.partspracing.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "PartsList")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartsContainer {
    @XmlElement(name = "Part")
    private List<RosskoPartDto> parts;

    public List<RosskoPartDto> getParts() {
        return parts;
    }

    public void setParts(List<RosskoPartDto> parts) {
        this.parts = parts;
    }
}
