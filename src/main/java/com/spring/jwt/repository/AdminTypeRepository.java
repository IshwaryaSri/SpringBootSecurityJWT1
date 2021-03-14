package com.spring.jwt.repository;

import com.spring.jwt.entity.AdminTypes;
import com.spring.jwt.entity.MerchantTypes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminTypeRepository extends CrudRepository<AdminTypes, Integer> {

    List<AdminTypes> findAll();

    AdminTypes findTypeById(Integer id);
}
