package com.example.xmlexercisepartone.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryExportRootDTO {
    @XmlElement(name = "category")
    private List<CategoryExportDTO> categories;

    public CategoryExportRootDTO() {
    }

    public List<CategoryExportDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryExportDTO> categories) {
        this.categories = categories;
    }
}
