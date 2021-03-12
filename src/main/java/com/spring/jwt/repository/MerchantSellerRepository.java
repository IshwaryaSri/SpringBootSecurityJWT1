package com.spring.jwt.repository;

import com.spring.jwt.entity.MerchantTypes;
import com.spring.jwt.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantSellerRepository extends CrudRepository<Merchants, Integer> {

    Merchants findByEmail(String email);

    boolean existsByEmail(String email);





}
