package com.example.xmlexercisepartone.dtos.export;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserExportDTO {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlElement(name = "products")
    private ProductExportUserRootDTO sold;

    public UserExportDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ProductExportUserRootDTO getSold() {
        return sold;
    }

    public void setSold(ProductExportUserRootDTO sold) {
        this.sold = sold;
    }
}
