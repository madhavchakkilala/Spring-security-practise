package com.store.online.Ecom.Service;

import com.store.online.Ecom.Entity.User;
import com.store.online.Ecom.Exception.ResourceUnavailableException;
import com.store.online.Ecom.Models.UserDto;
import com.store.online.Ecom.Models.UserPrincipal;
import com.store.online.Ecom.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncryptionService passwordEncryptionService;


    @Override
    public User registerUser(UserDto userDetails) {
        String encryptedPassword = passwordEncryptionService.encryptPassword(userDetails.getPassword());
        User user = new User();
        user.setEmail(userDetails.getEmail());
        user.setUserName(userDetails.getUserName());
        user.setPassword(encryptedPassword);
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setRole("User");
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User findByEmailId(String email) throws UsernameNotFoundException {
        User user = userRepository.findById(email).orElseThrow(() -> new UsernameNotFoundException("User with given email doesnt exit"));
        return user;
    }


    @Override
    public UserPrincipal loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findById(email).orElseThrow(() -> new UsernameNotFoundException("User with given email doesnt exit"));
        return UserPrincipal.create(user);
    }




}
