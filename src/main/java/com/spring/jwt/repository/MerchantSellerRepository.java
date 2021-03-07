package com.spring.jwt.repository;

import com.spring.jwt.entity.MerchantSeller;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantSellerRepository extends CrudRepository<MerchantSeller, Integer> {

    MerchantSeller findByUsername(String username);

    boolean existsByUsername(String username);
}
