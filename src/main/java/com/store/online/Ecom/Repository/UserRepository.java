package com.store.online.Ecom.Repository;

import com.store.online.Ecom.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
