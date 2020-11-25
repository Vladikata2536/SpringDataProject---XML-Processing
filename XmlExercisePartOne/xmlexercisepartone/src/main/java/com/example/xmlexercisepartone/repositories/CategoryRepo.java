package com.example.xmlexercisepartone.repositories;


import com.example.xmlexercisepartone.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long> {
    @Query("SELECT c FROM Category c ORDER BY c.products.size DESC ")
    Set<Category> getAllProductsSorted();
}
