package com.example.xmlexercisepartone.dtos.export;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductUserLastExportRootDTO {
    @XmlAttribute
    private int count;
    @XmlElement(name = "product")
    private List<ProductUserLastExportDTO> sold;

    public ProductUserLastExportRootDTO() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductUserLastExportDTO> getSold() {
        return sold;
    }

    public void setSold(List<ProductUserLastExportDTO> sold) {
        this.sold = sold;
    }
}
