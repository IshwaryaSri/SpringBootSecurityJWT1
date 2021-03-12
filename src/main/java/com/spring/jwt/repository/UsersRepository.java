package com.spring.jwt.repository;

import com.spring.jwt.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Integer> {

    boolean existsByEmail(String email);

    Users findByEmail(String email);


}

