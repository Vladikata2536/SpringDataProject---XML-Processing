package com.example.xmlexercisepartone.services;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ProductService {
    void seedProducts() throws Exception;
    void getAllProductsByPriceRange() throws IOException, JAXBException;
}
