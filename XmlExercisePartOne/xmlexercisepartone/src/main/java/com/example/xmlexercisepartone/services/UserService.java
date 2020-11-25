package com.example.xmlexercisepartone.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface UserService {
    void seedUsers() throws IOException, JAXBException;
    void seedFriends() throws Exception;
    void getAllUsersBySoldProducts() throws IOException, JAXBException;
    void getAllUsersLast() throws IOException, JAXBException;
}
