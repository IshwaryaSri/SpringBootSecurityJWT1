package com.spring.jwt.service;

import com.spring.jwt.MerchantPrincipal;
import com.spring.jwt.entity.MerchantTypes;
import com.spring.jwt.entity.Merchants;
import com.spring.jwt.repository.MerchantSellerRepository;
import com.spring.jwt.repository.MerchantTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class MerchantSellerService implements UserDetailsService {

    @Autowired
    MerchantSellerRepository merchantRepository;

    @Autowired
    MerchantTypeRepository merchanTypeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Merchants merchant = merchantRepository.findByEmail(email);
        if(merchant==null)
            throw new UsernameNotFoundException("User 404");

        return new MerchantPrincipal(merchant);
    }

    public Merchants save(Merchants merchant,int typeId) {
        Merchants merchantdetails = new Merchants();
        merchantdetails.setEmail(merchant.getEmail());
        merchantdetails.setPassword(passwordEncoder.encode(merchant.getPassword()));
        MerchantTypes types = merchanTypeRepository.findTypeById(typeId);
        merchantdetails.setMerchantTypes(types);
        merchantdetails.setVerified(false);
        merchantRepository.save(merchantdetails);
        return merchantdetails;
    }

    public Merchants saveToken(String email, String token) {
        Merchants merchant = merchantRepository.findByEmail(email);
        merchant.setAccessToken(token);
        merchantRepository.save(merchant);
        return merchant;
    }

    public boolean existsByUsername(String email){
        boolean merchantExists = false;
        if(merchantRepository.existsByEmail(email)){
            merchantExists =true;
        }
        return merchantExists;
    }


}
