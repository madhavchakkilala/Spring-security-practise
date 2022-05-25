package com.store.online.Ecom.Controller;


import com.store.online.Ecom.Entity.User;
import com.store.online.Ecom.Exception.ResourceUnavailableException;
import com.store.online.Ecom.Models.CartProductRemoveRequestDto;
import com.store.online.Ecom.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/Cart")
public class CartController{


    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity addItemToCart(@RequestAttribute("userEmail") String currentUser){
        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping
    public ResponseEntity removeItemsFromCart(@RequestAttribute("userEmail") String currentUser,CartProductRemoveRequestDto productDetails) throws ResourceUnavailableException {
    	User modifiedUser = cartService.removeCartFromProduct(currentUser, productDetails.getProductName());
    	return ResponseEntity.ok(modifiedUser);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> removeAllItemsFromCart(@RequestAttribute("userEmail") String currentUser) {
        cartService.clearCartForUser(currentUser);
        return ResponseEntity.ok("Removed all products from cart");
    }
}
