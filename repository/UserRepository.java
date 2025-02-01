package com.PizzaJB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PizzaJB.entity.UsersModel;

@Repository
public interface UserRepository extends JpaRepository<UsersModel, Long> {
  UsersModel findByEmail(String email);

}
