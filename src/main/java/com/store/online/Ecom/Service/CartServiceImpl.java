package com.store.online.Ecom.Service;

import com.store.online.Ecom.Entity.Product;
import com.store.online.Ecom.Entity.User;
import com.store.online.Ecom.Exception.ResourceUnavailableException;
import com.store.online.Ecom.Repository.ProductRepository;
import com.store.online.Ecom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public User addProductToCart(String userEmail, Product product) {
        User currentUser = userService.findByEmailId(userEmail);
        currentUser.getProducts().add(product);
        User user = userRepository.save(currentUser);
        return user;
    }

    @Override
    @Transactional
    public void clearCartForUser(String userEmail) {
        User currentUser = userService.findByEmailId(userEmail);
        for(Product product : currentUser.getProducts()){
            currentUser.getProducts().remove(product);
        }
        User user = userRepository.save(currentUser);
    }

    @Override
    public User removeCartFromProduct(String userEmail, String productId) throws ResourceUnavailableException {
        User currentUser = userService.findByEmailId(userEmail);
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceUnavailableException());
        for(Product productToBeRemoved : currentUser.getProducts()){
            if(productToBeRemoved.getName().equals(product.getName())){
                currentUser.getProducts().remove(productToBeRemoved);
            }
        }
        User updatedUser = userRepository.save(currentUser);
        return updatedUser;
    }
}
