package com.PizzaJB.services.imp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PizzaJB.dto.UserResponseDto;
import com.PizzaJB.entity.OrderModel;
import com.PizzaJB.entity.PizzaModel;
import com.PizzaJB.entity.UserModel;
import com.PizzaJB.enums.PizzaStatusEnum;
import com.PizzaJB.repository.OrderRespositoy;
import com.PizzaJB.services.OrderService;
import com.PizzaJB.services.PizzaService;
import com.PizzaJB.services.UserService;

@Service
public class OrderServiceImp implements OrderService {
  @Autowired
  private PizzaService pizzaService;

  @Autowired
  private OrderRespositoy orderRespositoy;

  @Autowired
  private UserService userService;

  @Override
  public OrderModel createOrder(String email, List<Long> pizzaIds) {
    List<PizzaModel> pizzas = pizzaService.findByIdIn(pizzaIds);

    BigDecimal totalPrice = pizzas.stream()
        .map(PizzaModel::getPrice)
        .reduce(BigDecimal.ZERO, BigDecimal::add);

    List<OrderModel> existingOrders = orderRespositoy.findOrderByUserEmail(email);
    existingOrders.forEach(order -> System.out.println("ID: " + order.getId() + " | Total: " + order.getTotalPrice()));

    BigDecimal previousOrdersTotal = existingOrders.isEmpty() ? BigDecimal.ZERO
        : existingOrders.get(existingOrders.size() - 1).getTotalPrice();

    BigDecimal finalTotalPrice = totalPrice.add(previousOrdersTotal);

    OrderModel order = new OrderModel();
    order.setTotalPrice(finalTotalPrice);
    order.setStatus(PizzaStatusEnum.EM_PROCESSAMENTO);
    order.setPizzas(pizzas);

    UserResponseDto userResponseDto = userService.findUserByEmail(email).getBody();
    UserModel userModel = new UserModel(userResponseDto.id(),
        userResponseDto.name(),
        userResponseDto.email());
    order.setUser(userModel);

    return orderRespositoy.save(order);
  }

  @Override
  public List<OrderModel> getAllOrders() {
    return orderRespositoy.findAll();

  }

  @Override
  public String deleteOrders(Long id) {
    orderRespositoy.deleteById(id);
    return "Order deleted successfully";
  }

  @Override
  public OrderModel updateStatusPizza(Long id, PizzaStatusEnum status) {
    Optional<OrderModel> orderOptional = orderRespositoy.findById(id);
    if (orderOptional.isPresent()) {
      OrderModel order = orderOptional.get();
      order.setStatus(status);
      return orderRespositoy.save(order); // Salva a ordem com o novo status
    }
    throw new RuntimeException("Order not found");
  }

}
