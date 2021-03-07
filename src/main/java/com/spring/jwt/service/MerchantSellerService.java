package com.spring.jwt.service;

import com.spring.jwt.MerchantPrincipal;
import com.spring.jwt.UserPrincipal;
import com.spring.jwt.entity.MerchantSeller;
import com.spring.jwt.entity.Users;
import com.spring.jwt.repository.MerchantSellerRepository;
import com.spring.jwt.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MerchantSellerService implements UserDetailsService {

    @Autowired
    MerchantSellerRepository merchantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MerchantSeller merchant = merchantRepository.findByUsername(userName);
        if(merchant==null)
            throw new UsernameNotFoundException("User 404");

        return new MerchantPrincipal(merchant);
    }

    public MerchantSeller save(MerchantSeller merchant) {
        MerchantSeller merchantdetails = new MerchantSeller();
        merchantdetails.setUsername(merchant.getUsername());
        merchantdetails.setPassword(passwordEncoder.encode(merchant.getPassword()));
        merchantdetails.setName(merchant.getName());
        merchantdetails.setMail(merchant.getMail());
        merchantRepository.save(merchantdetails);
        return merchantdetails;
    }

    public MerchantSeller saveToken(String username,String token) {
        MerchantSeller merchant = merchantRepository.findByUsername(username);
        merchant.setAccessToken(token);
        merchantRepository.save(merchant);
        return merchant;
    }

    public boolean existsByUsername(String username){
        boolean merchantExists = false;
        if(merchantRepository.existsByUsername(username)){
            merchantExists =true;
        }
        return merchantExists;
    }
}
