package com.example.xmlexercisepartone.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface CategoryService {
    void seedCategories() throws Exception;
    void getAllCategories() throws IOException, JAXBException;
}
