package com.spring.jwt.controller;

import com.spring.jwt.entity.Users;
import com.spring.jwt.models.AuthenticationRequest;
import com.spring.jwt.models.AuthenticationResponse;
import com.spring.jwt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {

    @Autowired
    UsersService usersService;

    @GetMapping("/")
    public String index() {
        return "dashboard";
    }

    @GetMapping("/user/login")
    public String login() {
        return "welcomeUser";
    }

    @GetMapping("/user/signup")
    public String signUp() {

        return "usersignup";
    }

    @PostMapping("/user/signup")
    public String saveUser(Users user) throws Exception {
        boolean userExists = usersService.existsByEmail(user.getEmail());
        if(userExists!=true) {
            Users newUser = usersService.save(user);
            return "signupsuccess";
        }else{
            return "error";
        }
//        return ResponseEntity.ok(usersService.save(user));
    }

    @GetMapping("/user/home")
    public String home() throws Exception{
        return "welcomeUser";
    }

    @GetMapping("/user/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/user/login?logout=true";
    }
}
