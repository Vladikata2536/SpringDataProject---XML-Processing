package com.example.xmlexercisepartone.services;

import com.example.xmlexercisepartone.dtos.export.ProductExportDTO;
import com.example.xmlexercisepartone.dtos.export.ProductExportRootDTO;
import com.example.xmlexercisepartone.dtos.importDTO.ProductImportDTO;
import com.example.xmlexercisepartone.dtos.importDTO.ProductImportRootDTO;
import com.example.xmlexercisepartone.entities.Category;
import com.example.xmlexercisepartone.entities.Product;
import com.example.xmlexercisepartone.entities.User;
import com.example.xmlexercisepartone.repositories.CategoryRepo;
import com.example.xmlexercisepartone.repositories.ProductRepo;
import com.example.xmlexercisepartone.repositories.UserRepo;
import com.example.xmlexercisepartone.utils.XmlParser;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final String PRODUCT_PATH = "src/main/resources/09. XML-Processing-Exercises/products.xml";
    private final String PRODUCT_EXPORT_FIRST_PATH = "src/main/resources/09. XML-Processing-Exercises/exported/first";

    private final ModelMapper modelMapper;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final CategoryRepo categoryRepo;
    private final XmlParser xmlParser;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepo productRepo, UserRepo userRepo, CategoryRepo categoryRepo, XmlParser xmlParser) {
        this.modelMapper = modelMapper;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.xmlParser = xmlParser;
    }

    @Override
    @Transactional
    public void seedProducts() throws Exception {

        ProductImportRootDTO rootDTO = this.xmlParser.parseXml(ProductImportRootDTO.class, PRODUCT_PATH);

        for (ProductImportDTO dtoProduct : rootDTO.getProducts()) {
            Product product = this.modelMapper.map(dtoProduct, Product.class);
            int randomNumber = getRandomNumber();
            if(randomNumber % 2 == 0) {
                User randomBuyer = getRandomUser();
                product.setBuyer(randomBuyer);
                Optional<User> byId = this.userRepo.findById(randomBuyer.getId());
                byId.ifPresent(user -> user.getBought().add(product));
            }else {
                product.setBuyer(null);
            }
            User randomSeller = getRandomUser();
            product.setSeller(randomSeller);
            product.setCategories(getRandomCategories());
            Optional<User> byId = this.userRepo.findById(randomSeller.getId());
            if(randomNumber % 2 == 0){
                byId.ifPresent(user -> user.getSold().add(product));
            }
            this.productRepo.saveAndFlush(product);
        }
    }

    @Override
    public void getAllProductsByPriceRange() throws JAXBException {
        Set<Product> products = this.productRepo.getAllProductsByPriceRange();
        ProductExportRootDTO rootDTO = new ProductExportRootDTO();
        List<ProductExportDTO> dtos = new ArrayList<>();

        for (Product product : products) {
            ProductExportDTO dto = this.modelMapper.map(product, ProductExportDTO.class);
            dto.setSeller(product.getSeller().getFirstName() + " " + product.getSeller().getLastName());
            dtos.add(dto);
        }
        rootDTO.setProducts(dtos);
        this.xmlParser.exportXml(rootDTO,ProductExportRootDTO.class,PRODUCT_EXPORT_FIRST_PATH);

    }

    private Set<Category> getRandomCategories() throws Exception {
        Set<Category> categories = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Category category = this.getRandomCategory();
            categories.add(category);
        }
        return categories;

    }

    private Category getRandomCategory() throws Exception {
        Random random = new Random();
        long index = (long) random.nextInt((int) this.categoryRepo.count()) + 1;
        Optional<Category> category = this.categoryRepo.findById(index);
        if (category.isPresent()) {
            return category.get();
        } else {
            throw new Exception("Invalid part id");
        }

    }

    private User getRandomUser() throws Exception {
        Random random = new Random();
        long index = (long) random.nextInt((int) this.userRepo.count()) + 1;
        Optional<User> part = this.userRepo.findById(index);
        if (part.isPresent()) {
            return part.get();
        } else {
            throw new Exception("Invalid part id");
        }
    }
    private int getRandomNumber(){
        Random random = new Random();
        return random.nextInt((int) this.userRepo.count()) + 1;
    }
}
