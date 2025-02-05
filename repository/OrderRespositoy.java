package com.PizzaJB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.PizzaJB.entity.OrderModel;

public interface OrderRespositoy extends JpaRepository<OrderModel, Long> {
  List<OrderModel> findOrderByUserEmail(String email);
}
