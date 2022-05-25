package com.store.online.Ecom.Controller;

import com.store.online.Ecom.Entity.Product;
import com.store.online.Ecom.Service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/v1")
public class ProductsController {


    @Autowired
    private ProductServiceImpl productServiceImpl;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(@RequestParam("pageNumber") int pageNumber,@RequestParam(value = "name",required = false) String productName,@RequestParam(value = "minPrice",required = false) Long minimumPrice,@RequestParam(value = "maxPrice",required = false) Long maxPrice){
        List<Product> products = productServiceImpl.getAllProducts(pageNumber);
        return ResponseEntity.ok(products);
    }
}
