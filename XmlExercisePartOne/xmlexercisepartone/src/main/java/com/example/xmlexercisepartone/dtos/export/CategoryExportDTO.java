package com.example.xmlexercisepartone.dtos.export;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryExportDTO {
    @XmlAttribute
    private String name;
    @XmlElement(name = "products-count")
    private int productsCount;
    @XmlElement(name = "average-price")
    private double averagePrice;
    @XmlElement(name = "total-revenue")
    private double totalRevenue;

    public CategoryExportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
