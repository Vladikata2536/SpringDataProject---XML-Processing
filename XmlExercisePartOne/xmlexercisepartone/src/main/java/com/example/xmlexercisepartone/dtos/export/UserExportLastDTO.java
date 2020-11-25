package com.example.xmlexercisepartone.dtos.export;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserExportLastDTO {
    @XmlAttribute(name = "first-name")
    private String firstName;
    @XmlAttribute(name = "last-name")
    private String lastName;
    @XmlAttribute
    private int age;
    @XmlElement(name = "sold-products")
    private ProductUserLastExportRootDTO sold;

    public UserExportLastDTO() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProductUserLastExportRootDTO getSold() {
        return sold;
    }

    public void setSold(ProductUserLastExportRootDTO sold) {
        this.sold = sold;
    }
}
