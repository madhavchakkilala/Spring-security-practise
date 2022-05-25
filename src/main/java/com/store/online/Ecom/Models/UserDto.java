package com.store.online.Ecom.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;
}
