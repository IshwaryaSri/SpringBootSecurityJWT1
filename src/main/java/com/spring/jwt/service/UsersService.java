package com.spring.jwt.service;

import com.spring.jwt.UserPrincipal;
import com.spring.jwt.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.spring.jwt.repository.UsersRepository;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(userName);
        if(user==null)
            throw new UsernameNotFoundException("User 404");

        return new UserPrincipal(user);
    }

    public Users save(Users user) {
        Users userdetails = new Users (user.getUsername(),passwordEncoder.encode(user.getPassword()));
        userRepository.save(userdetails);
        return userdetails;
    }

    public Users saveToken(String username,String token) {
        Users user = userRepository.findByUsername(username);
        user.setToken(token);
        userRepository.save(user);
        return user;
    }

    public boolean existsByUsername(String username){
        boolean userExists = false;
        if(userRepository.existsByUsername(username)){
            userExists =true;
        }
        return userExists;
    }
}
