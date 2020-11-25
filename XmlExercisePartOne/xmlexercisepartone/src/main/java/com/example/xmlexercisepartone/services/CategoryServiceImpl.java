package com.example.xmlexercisepartone.services;

import com.example.xmlexercisepartone.dtos.export.CategoryExportDTO;
import com.example.xmlexercisepartone.dtos.export.CategoryExportRootDTO;
import com.example.xmlexercisepartone.dtos.importDTO.CategoryImportDTO;
import com.example.xmlexercisepartone.dtos.importDTO.CategoryImportRootDTO;
import com.example.xmlexercisepartone.entities.Category;
import com.example.xmlexercisepartone.entities.Product;
import com.example.xmlexercisepartone.repositories.CategoryRepo;
import com.example.xmlexercisepartone.utils.XmlParser;
import com.google.gson.Gson;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private static String CATEGORY_PATH = "src/main/resources/09. XML-Processing-Exercises/categories.xml";
    private static String CATEGORY_EXPORT_PATH = "src/main/resources/09. XML-Processing-Exercises/exported/third";

    private final ModelMapper modelMapper;
    private final CategoryRepo categoryRepo;
    private final XmlParser xmlParser;


    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepo categoryRepo, XmlParser xmlParser) {
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.categoryRepo = categoryRepo;
    }

    @Override
    @Transactional
    public void seedCategories() throws Exception {
        CategoryImportRootDTO rootDTO = this.xmlParser.parseXml(CategoryImportRootDTO.class, CATEGORY_PATH);
        for (CategoryImportDTO categoryImportDTO : rootDTO.getCategories()) {
            Category category = this.modelMapper.map(categoryImportDTO, Category.class);
            this.categoryRepo.saveAndFlush(category);
        }
    }

    @Override
    public void getAllCategories() throws JAXBException {
        Set<Category> categories = this.categoryRepo.getAllProductsSorted();
        CategoryExportRootDTO rootDTO = new CategoryExportRootDTO();
        List<CategoryExportDTO> dtos = new ArrayList<>();

        for (Category category : categories) {
            CategoryExportDTO dto = this.modelMapper.map(category, CategoryExportDTO.class);

            dto.setProductsCount(category.getProducts().size());
            Set<Product> products = category.getProducts();
            double average = products.stream().mapToDouble(p -> p.getPrice().doubleValue()).average().getAsDouble();
            dto.setAveragePrice(average);
            double sum = products.stream().mapToDouble(p -> p.getPrice().doubleValue()).sum();
            dto.setTotalRevenue(sum);
            dtos.add(dto);
        }
        rootDTO.setCategories(dtos);
        this.xmlParser.exportXml(rootDTO,CategoryExportRootDTO.class,CATEGORY_EXPORT_PATH);

    }


}
