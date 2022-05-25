package com.store.online.Ecom;


import com.store.online.Ecom.Entity.Product;
import com.store.online.Ecom.Entity.User;
import com.store.online.Ecom.Repository.ProductRepository;
import com.store.online.Ecom.Repository.UserRepository;
import com.store.online.Ecom.Service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;


@SpringBootTest
public class CartServiceTests {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    private String userEmail = "temp.tem@gmail.com";

    @Test
    @Transactional
    public void testAddProductAndClearCart(){
        Pageable pageable = PageRequest.of(0,1);
        Product product = productRepository.findAll(pageable).getContent().get(0);
        User user = userRepository.findById(userEmail).get();
        cartService.addProductToCart(userEmail,product);
        for(Product productInCart : user.getProducts()){
            if(productInCart.getId().equals(product.getId())){
                break;
            }
        }
        cartService.clearCartForUser(user.getEmail());
        Assertions.assertEquals(user.getProducts().size(),0);
    }



}
