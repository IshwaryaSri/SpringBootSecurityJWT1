package com.spring.jwt.controller;

import com.spring.jwt.entity.Users;
import com.spring.jwt.models.AuthenticationRequest;
import com.spring.jwt.models.AuthenticationResponse;
import com.spring.jwt.service.UsersService;
import com.spring.jwt.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {

    @Autowired
    UsersService usersService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestBody AuthenticationRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        }catch (BadCredentialsException be){
            throw new Exception("Invalid Credentials!!",be);
        }

        final UserDetails userDetails = usersService.loadUserByUsername(authRequest.getUsername());

        String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/user/signup")
    public ResponseEntity<?> saveUser(@RequestBody Users user) throws Exception {
        return ResponseEntity.ok(usersService.save(user));
    }

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        HttpHeaders responseHeaders = new HttpHeaders();
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        final UserDetails userDetails = usersService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        AuthenticationResponse response = new AuthenticationResponse(jwt);


        return new ResponseEntity<>(response, responseHeaders, HttpStatus.OK);
    }


}
