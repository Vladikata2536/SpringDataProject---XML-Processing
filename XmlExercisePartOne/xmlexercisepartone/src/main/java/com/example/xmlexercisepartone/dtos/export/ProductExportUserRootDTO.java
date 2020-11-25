package com.example.xmlexercisepartone.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductExportUserRootDTO {
    @XmlElement(name = "product")
    private List<ProductExportUserDTO> sold;

    public ProductExportUserRootDTO() {
    }

    public List<ProductExportUserDTO> getSold() {
        return sold;
    }

    public void setSold(List<ProductExportUserDTO> sold) {
        this.sold = sold;
    }
}
