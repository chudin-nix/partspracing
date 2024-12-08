package com.example.partspracing.entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.math.BigDecimal;
@XmlAccessorType(XmlAccessType.FIELD)
public class EmexPartDto{
    @XmlElement(name = "GroupId")
    private int groupId;
    @XmlElement(name = "PriceGroup")
    private String priceGroup;

    @XmlElement(name = "MakeLogo")
    private String makeLogo;

    @XmlElement(name = "MakeName")
    private String makeName;

    @XmlElement(name = "DetailNum")
    private String detailNum;

    @XmlElement(name = "NewDetailNum")
    private String newDetailNum;

    @XmlElement(name = "DetailNameRus")
    private String detailNameRus;

    @XmlElement(name = "PriceLogo")
    private String priceLogo;

    @XmlElement(name = "DestinationLogo")
    private String destinationLogo;

    @XmlElement(name = "PriceCountry")
    private String priceCountry;

    @XmlElement(name = "LotQuantity")
    private int lotQuantity;

    @XmlElement(name = "Quantity")
    private int quantity;

    @XmlElement(name = "DDPercent")
    private double dDPercent;
    @XmlElement(name = "ADDays")
    private int aDDays;

    @XmlElement(name = "ResultPrice")
    private BigDecimal resultPrice;

    @XmlElement(name = "DeliverTimeGuaranteed")
    private String deliverTimeGuaranteed;

    public int getGroupId() {
        return groupId;
    }

    public String getPriceGroup() {
        return priceGroup;
    }

    public String getMakeLogo() {
        return makeLogo;
    }

    public String getMakeName() {
        return makeName;
    }

    public String getDetailNum() {
        return detailNum;
    }

    public String getNewDetailNum() {
        return newDetailNum;
    }

    public String getDetailNameRus() {
        return detailNameRus;
    }

    public String getPriceLogo() {
        return priceLogo;
    }

    public String getDestinationLogo() {
        return destinationLogo;
    }

    public String getPriceCountry() {
        return priceCountry;
    }

    public int getLotQuantity() {
        return lotQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getdDPercent() {
        return dDPercent;
    }

    public int getADDays() {
        return aDDays;
    }

    public BigDecimal getResultPrice() {
        return resultPrice;
    }

    public String getDeliverTimeGuaranteed() {
        return deliverTimeGuaranteed;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public void setPriceGroup(String priceGroup) {
        this.priceGroup = priceGroup;
    }

    public void setMakeLogo(String makeLogo) {
        this.makeLogo = makeLogo;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public void setDetailNum(String detailNum) {
        this.detailNum = detailNum;
    }

    public void setNewDetailNum(String newDetailNum) {
        this.newDetailNum = newDetailNum;
    }

    public void setDetailNameRus(String detailNameRus) {
        this.detailNameRus = detailNameRus;
    }

    public void setPriceLogo(String priceLogo) {
        this.priceLogo = priceLogo;
    }

    public void setDestinationLogo(String destinationLogo) {
        this.destinationLogo = destinationLogo;
    }

    public void setPriceCountry(String priceCountry) {
        this.priceCountry = priceCountry;
    }

    public void setLotQuantity(int lotQuantity) {
        this.lotQuantity = lotQuantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setdDPercent(double dDPercent) {
        this.dDPercent = dDPercent;
    }

    public void setADDays(int aDDays) {
        this.aDDays = aDDays;
    }

    public void setResultPrice(BigDecimal resultPrice) {
        this.resultPrice = resultPrice;
    }

    public void setDeliveryRegionType(String deliverTimeGuaranteed) {
        this.deliverTimeGuaranteed = deliverTimeGuaranteed;
    }
}
