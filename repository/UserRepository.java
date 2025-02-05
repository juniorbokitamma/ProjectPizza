package com.PizzaJB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PizzaJB.entity.*;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
  UserModel findByEmail(String email);

}
