package com.example.xmlexercisepartone;


import com.example.xmlexercisepartone.services.CategoryService;
import com.example.xmlexercisepartone.services.ProductService;
import com.example.xmlexercisepartone.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class AppInitializer implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public AppInitializer(CategoryService categoryService, ProductService productService, UserService userService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run(String... args) throws Exception {
// THIS IS THE FIRST PART OF THE EXERCISE. IT'S ABOUT THE PRODUCTS SHOP DATABASE
        Loop:
        while (true) {
            System.out.printf("THIS IS THE FIRST PART OF THE EXERCISE. IT'S ABOUT THE PRODUCTS SHOP DATABASE" +
                    "%nENTER ONE OF THE COMMANDS BELOW TO PROCEED:%n\t1- seedData%n\t2- first%n\t3- second%n\t4- third%n\t5- fourth%n\t6- exit%n");
            String inputData = this.bufferedReader.readLine();
            switch (inputData) {
                case "seedData":
                    this.userService.seedUsers();
                    this.categoryService.seedCategories();
                    this.productService.seedProducts();
                    this.userService.seedFriends();
                    break;
                case "first":
                    this.productService.getAllProductsByPriceRange();
                    break;
                case "second":
                    this.userService.getAllUsersBySoldProducts();
                    break;
                case "third":
                    this.categoryService.getAllCategories();
                    break;
                case "fourth":
                    this.userService.getAllUsersLast();
                    break;

                case "exit":
                    System.err.println("PROGRAM CLOSED");
                    break Loop;
                default:
                    System.out.println("WRONG COMMAND TRY AGAIN");
                    break ;
            }
        }


    }
}
