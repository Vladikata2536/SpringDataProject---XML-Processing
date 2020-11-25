package com.example.xmlexercisepartone.repositories;


import com.example.xmlexercisepartone.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

    @Query("SELECT p FROM Product p WHERE p.price between 500 AND 1000 AND p.buyer IS NULL ORDER BY p.price")
    Set<Product> getAllProductsByPriceRange();
}
