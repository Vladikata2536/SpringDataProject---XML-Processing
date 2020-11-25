package com.example.xmlexercisepartone.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserExportRootDTO {
    @XmlElement(name = "user")
    private List<UserExportDTO> sold;

    public UserExportRootDTO() {
    }

    public List<UserExportDTO> getSold() {
        return sold;
    }

    public void setSold(List<UserExportDTO> sold) {
        this.sold = sold;
    }
}
