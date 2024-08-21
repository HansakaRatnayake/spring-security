package com.dhailer.spring_security.controller;

import com.dhailer.spring_security.model.LoginUser;
import com.dhailer.spring_security.model.User;
import com.dhailer.spring_security.service.LoginUserService;
import com.dhailer.spring_security.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginUserService loginUserService;

    @GetMapping("/student")
    public List<User> student(HttpServletRequest httpServletRequest) {
        return userService.findAll();
    }

    @GetMapping("/csrf-token")
    public CsrfToken csrfToken(HttpServletRequest httpServletRequest) {
        return (CsrfToken) httpServletRequest.getAttribute("_csrf");
    }

    @PostMapping("/student/register")
    public String studentPost(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUser user) {
        return loginUserService.verify(user);
    }

}
