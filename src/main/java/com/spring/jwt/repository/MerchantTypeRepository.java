package com.spring.jwt.repository;

import com.spring.jwt.entity.MerchantTypes;
import com.spring.jwt.entity.Merchants;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MerchantTypeRepository extends CrudRepository<MerchantTypes, Integer> {

    List<MerchantTypes> findAll();

    MerchantTypes findTypeById(Integer id);
}
