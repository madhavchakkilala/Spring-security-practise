package com.store.online.Ecom.Repository;

import com.store.online.Ecom.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
