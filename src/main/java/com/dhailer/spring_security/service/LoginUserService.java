package com.dhailer.spring_security.service;

import com.dhailer.spring_security.model.LoginUser;
import com.dhailer.spring_security.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginUserService {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    public String verify(LoginUser user) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authenticate.isAuthenticated()) return  jwtService.getToken(user.getUsername());

        return "authentication failed";
    }
}
