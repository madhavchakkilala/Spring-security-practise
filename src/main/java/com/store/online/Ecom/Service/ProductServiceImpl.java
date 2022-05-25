package com.store.online.Ecom.Service;

import com.store.online.Ecom.Entity.Product;
import com.store.online.Ecom.Exception.ResourceUnavailableException;
import com.store.online.Ecom.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    public Product addProduct(Product product){
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    public List<Product> getAllProducts(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,20);
        Page<Product> page = productRepository.findAll(pageable);
        return page.getContent();
    }

    public Product updateProduct(String productId,Product product) throws ResourceUnavailableException {
        Optional<Product> productToBeUpdated = productRepository.findById(productId);
        Product updatedProduct = Optional.ofNullable(productToBeUpdated).get().orElseThrow(ResourceUnavailableException::new);
        return updatedProduct;
    }

    public Product blockProduct(String productId) throws ResourceUnavailableException{
        Optional<Product> productToBeUpdated = productRepository.findById(productId);
        Product product = Optional.ofNullable(productToBeUpdated).get().orElseThrow(ResourceUnavailableException::new);
        product.setBlocked(true);
        return product;
    }
}
