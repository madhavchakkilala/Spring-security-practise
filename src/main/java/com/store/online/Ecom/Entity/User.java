package com.store.online.Ecom.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    @Id
    private String email;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String role;
    private String image_url;
    @ManyToMany(mappedBy = "users")
    private Set<Product> products;
}
