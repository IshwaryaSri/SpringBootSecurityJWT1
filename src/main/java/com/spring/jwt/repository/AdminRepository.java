package com.spring.jwt.repository;

import com.spring.jwt.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

    Admin findByEmail(String email);

    boolean existsByEmail(String email);

}

