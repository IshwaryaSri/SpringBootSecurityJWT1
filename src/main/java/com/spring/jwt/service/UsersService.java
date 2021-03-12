package com.spring.jwt.service;

import com.spring.jwt.UserPrincipal;
import com.spring.jwt.entity.AuthenticationProvider;
import com.spring.jwt.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.spring.jwt.repository.UsersRepository;

import java.util.Date;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(email);
        if(user==null)
            throw new UsernameNotFoundException("User 404");

        return new UserPrincipal(user);
    }

    public Users save(Users user) {
        Users userdetails = new Users (user.getEmail(),passwordEncoder.encode(user.getPassword()));
        userRepository.save(userdetails);
        return userdetails;
    }

    public Users saveToken(String email,String token) {
        Users user = userRepository.findByEmail(email);
        user.setToken(token);
        userRepository.save(user);
        return user;
    }

    public boolean existsByEmail(String username){
        boolean userExists = false;
        if(userRepository.existsByEmail(username)){
            userExists =true;
        }
        return userExists;
    }

    public void saveOAuth2LoginUser(String email,AuthenticationProvider provider) {
        Users user = new Users();
        user.setEmail(email);
        user.setJoiningDate(new Date());
        user.setAuthProvider(provider);
        userRepository.save(user);
    }
}
