package com.dhailer.spring_security.repository;

import com.dhailer.spring_security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
