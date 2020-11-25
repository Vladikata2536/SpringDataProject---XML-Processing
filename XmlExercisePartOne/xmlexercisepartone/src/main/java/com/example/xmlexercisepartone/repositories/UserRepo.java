package com.example.xmlexercisepartone.repositories;


import com.example.xmlexercisepartone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.sold.size > 0 AND u.bought.size > 0 ORDER BY u.lastName,u.firstName")
    Set<User> getAllBySoldProducts();

    @Query("SELECT u FROM User u WHERE u.sold.size > 0 ORDER BY u.sold.size DESC, u.lastName")
    Set<User> getAllBySoldProductsLast();
}
