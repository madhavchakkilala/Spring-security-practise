package com.store.online.Ecom.Service;

import com.store.online.Ecom.Entity.Product;
import com.store.online.Ecom.Entity.User;
import com.store.online.Ecom.Exception.ResourceUnavailableException;

public interface CartService {
    User addProductToCart(String userEmail, Product product);
    void clearCartForUser(String userEmail);
    User removeCartFromProduct(String userEmail, String productId) throws ResourceUnavailableException;
}
