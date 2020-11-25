package com.example.xmlexercisepartone.dtos.export;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserExportLastRootDTO {
    @XmlElement(name = "user")
    private List<UserExportLastDTO> users;

    public UserExportLastRootDTO() {
    }

    public List<UserExportLastDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserExportLastDTO> users) {
        this.users = users;
    }
}
