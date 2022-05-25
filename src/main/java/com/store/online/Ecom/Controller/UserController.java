package com.store.online.Ecom.Controller;

import com.store.online.Ecom.Entity.User;
import com.store.online.Ecom.Models.JwtTokenRequest;
import com.store.online.Ecom.Models.JwtTokenResponse;
import com.store.online.Ecom.Models.UserDto;
import com.store.online.Ecom.Models.UserPrincipal;
import com.store.online.Ecom.Service.TokenProviderService;
import com.store.online.Ecom.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProviderService tokenProviderService;

    @PostMapping("/generate-token")
    public ResponseEntity generateToken(@RequestBody JwtTokenRequest jwtTokenRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtTokenRequest.getUsername(),jwtTokenRequest.getPassword()));
        String token = tokenProviderService.generateToken((UserPrincipal) authentication.getPrincipal());
        System.out.println("Token generated {} " + token);
        return ResponseEntity.ok(new JwtTokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody UserDto userDto){
        User user = userService.registerUser(userDto);
        return ResponseEntity.ok(user);
    }


    @PostMapping("/get_all_users")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.ok("Get users");
    }
}
