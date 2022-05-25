package com.store.online.Ecom.Service;

import com.store.online.Ecom.Entity.User;
import com.store.online.Ecom.Models.UserDto;

public interface UserService {
    public User registerUser(UserDto userDetails);
    User findByEmailId(String email);
}
