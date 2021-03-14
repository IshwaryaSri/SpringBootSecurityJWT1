package com.spring.jwt.service;


import com.spring.jwt.entity.AdminTypes;
import com.spring.jwt.entity.MerchantTypes;
import com.spring.jwt.repository.AdminTypeRepository;
import com.spring.jwt.repository.MerchantTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminTypeService {

    @Autowired
    AdminTypeRepository adminTypeRepository;

    public List<AdminTypes> findAll() {
        List<AdminTypes> adminTypes =  adminTypeRepository.findAll();
        return adminTypes;
    }
}
