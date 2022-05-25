package com.store.online.Ecom.Service;

import com.store.online.Ecom.Entity.Product;
import com.store.online.Ecom.Exception.ResourceUnavailableException;

import java.util.List;

public interface ProductService {
    public Product addProduct(Product product);
    public List<Product> getAllProducts(int pageNumber);
    public Product updateProduct(String productId,Product product) throws ResourceUnavailableException;
    public Product blockProduct(String productId) throws ResourceUnavailableException;
}
