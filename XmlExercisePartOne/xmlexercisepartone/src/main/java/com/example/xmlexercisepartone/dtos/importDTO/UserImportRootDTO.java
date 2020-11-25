package com.example.xmlexercisepartone.dtos.importDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImportRootDTO {
    @XmlElement(name = "user")
    private List<UserImportDTO> users;

    public UserImportRootDTO() {
    }

    public List<UserImportDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserImportDTO> users) {
        this.users = users;
    }
}
