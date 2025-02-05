package com.PizzaJB.services;

import org.springframework.stereotype.Service;

import java.util.List;
import com.PizzaJB.entity.OrderModel;
import com.PizzaJB.enums.PizzaStatusEnum;

@Service
public interface OrderService {
  public OrderModel createOrder(String email, List<Long> pizzaIds);

  public List<OrderModel> getAllOrders();

  public String deleteOrders(Long id);

  public OrderModel updateStatusPizza(Long id, PizzaStatusEnum newStatus);

}
