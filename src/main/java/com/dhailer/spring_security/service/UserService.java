package com.dhailer.spring_security.service;

import com.dhailer.spring_security.model.User;
import com.dhailer.spring_security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public List<User> findAll(){
        return userRepo.findAll();
    }

    public String createUser(User user) {

        try {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepo.save(user);
        }catch (Exception e) {
            throw new RuntimeException("Error creating user", e);
        }

        return "User created successfully";
    }


}
