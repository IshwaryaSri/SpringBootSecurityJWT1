package com.spring.jwt.service;

import com.spring.jwt.entity.MerchantTypes;
import com.spring.jwt.repository.MerchantTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantTypeService{


    @Autowired
    MerchantTypeRepository merchantTypeRepository;

    public List<MerchantTypes> findAll() {
        List<MerchantTypes> merchantTypes =  merchantTypeRepository.findAll();
        return merchantTypes;
    }
}
