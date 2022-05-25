package com.store.online.Ecom.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordEncryptionDto {
    private String passwordHash;
    private String passwordSalt;
}
