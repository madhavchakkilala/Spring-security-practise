package com.store.online.Ecom;

import com.store.online.Ecom.Entity.Category;
import com.store.online.Ecom.Entity.Product;
import com.store.online.Ecom.Service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class ProductRepositoryTests {
    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Test
    public void testInsertProduct(){
        Product productToTestForInsertion = new Product();
        productToTestForInsertion.setBlocked(false);
        productToTestForInsertion.setBrand("Gucci");
        productToTestForInsertion.setName("Hazard pant");
        productToTestForInsertion.setDescription("Cheap price best pants");
        productToTestForInsertion.setImageUrl("https://eckssaloon.com/pics/mens-stretch-waist-khaki-pants-3.png");
        Category category = new Category(1,"men");
        Set<Category> categories = new HashSet<>();
        categories.add((category));
        productToTestForInsertion.setCategories(categories);
        productServiceImpl.addProduct(productToTestForInsertion);

    }


}
