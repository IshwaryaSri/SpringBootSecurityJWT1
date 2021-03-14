package com.spring.jwt.service;


import com.spring.jwt.AdminPrincipal;
import com.spring.jwt.MerchantPrincipal;
import com.spring.jwt.entity.Admin;
import com.spring.jwt.entity.AdminTypes;
import com.spring.jwt.entity.MerchantTypes;
import com.spring.jwt.entity.Merchants;
import com.spring.jwt.repository.AdminRepository;
import com.spring.jwt.repository.AdminTypeRepository;
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
public class AdminService implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminTypeRepository adminTypeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email);
        if(admin==null)
            throw new UsernameNotFoundException("User 404");

        return new AdminPrincipal(admin);
    }

    public Admin save(Admin admin,int typeId) {
        Admin admindetails = new Admin();
        admindetails.setEmail(admin.getEmail());
        admindetails.setPassword(passwordEncoder.encode(admin.getPassword()));
        AdminTypes types = adminTypeRepository.findTypeById(typeId);
        admindetails.setAdminTypes(types);
        adminRepository.save(admindetails);
        return admindetails;
    }


    public boolean existsByUsername(String email){
        boolean merchantExists = false;
        if(adminRepository.existsByEmail(email)){
            merchantExists =true;
        }
        return merchantExists;
    }


}

