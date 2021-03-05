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
        Users newUser = new Users();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
}
