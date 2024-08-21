package com.dhailer.spring_security.service;

import com.dhailer.spring_security.model.User;
import com.dhailer.spring_security.model.UserPrinciple;
import com.dhailer.spring_security.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {


    private final UserRepo userRepo;

    @Autowired
    public MyUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User selecteduser = userRepo.findByUsername(username);

        if (selecteduser == null) {throw new UsernameNotFoundException("User not found");}

        return new UserPrinciple(List.of(new SimpleGrantedAuthority("USER"),new SimpleGrantedAuthority("MANAGER")),
                selecteduser.getUsername(),selecteduser.getPassword(),true,
                true,true,true);
    }
}
