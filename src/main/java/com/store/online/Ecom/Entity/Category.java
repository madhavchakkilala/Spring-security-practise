package com.store.online.Ecom.Entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    @Id
    private Integer id;
    private String name;
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;


    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
