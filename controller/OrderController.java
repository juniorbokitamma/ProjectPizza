package com.PizzaJB.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PizzaJB.dto.OrderDto;
import com.PizzaJB.dto.OrderResponseDto;
import com.PizzaJB.dto.OrderResponsesModelDto;
import com.PizzaJB.dto.PizzaDto;
import com.PizzaJB.dto.StatusUpdateDto;
import com.PizzaJB.dto.UserResponseDto;
import com.PizzaJB.entity.OrderModel;
import com.PizzaJB.enums.PizzaStatusEnum;
import com.PizzaJB.repository.OrderRespositoy;
import com.PizzaJB.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @Autowired
  private OrderRespositoy orderRespositoy;

  @PostMapping
  public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto,
      @AuthenticationPrincipal UserDetails userDetails) {
    String email = userDetails.getUsername();

    OrderModel order = orderService.createOrder(email, orderDto.pizzaIds());
    OrderResponsesModelDto response = new OrderResponsesModelDto(
        order.getId(),
        order.getPizzas(),
        order.getStatus(),
        order.getTotalPrice(),

        new UserResponseDto(order.getId(),
            order.getUser().getEmail(),
            order.getUser().getUsername()));
    return ResponseEntity.status(HttpStatus.CREATED).body(response);

  }

  @GetMapping("/")
  public List<OrderResponseDto> getAllOrders(@AuthenticationPrincipal UserDetails userDetails) {
    String email = userDetails.getUsername();

    List<OrderModel> orders = orderRespositoy.findOrderByUserEmail(email);

    return orders.stream().map(order -> {
      List<PizzaDto> pizzas = order.getPizzas().stream()
          .map(pizza -> new PizzaDto(
              pizza.getId(),
              pizza.getName(),
              pizza.getDescription(),
              pizza.getPrice()))
          .collect(Collectors.toList());

      UserResponseDto userDto = new UserResponseDto(
          order.getUser().getId(),
          order.getUser().getEmail(),
          order.getUser().getName());

      return new OrderResponseDto(
          order.getId(),
          pizzas,
          order.getStatus(),
          order.getTotalPrice(),
          userDto);
    }).collect(Collectors.toList());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
    String deleted = orderService.deleteOrders(id);
    if (deleted != null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Order deleted successfully");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("order not found");
    }

  }

  @PutMapping("/{id}/status")
  public ResponseEntity<String> updateOrderStatus(@PathVariable Long id,
      @RequestBody StatusUpdateDto statusUpdateDto) {
    try {
      PizzaStatusEnum status = PizzaStatusEnum.valueOf(statusUpdateDto.status());
      OrderModel updatedOrder = orderService.updateStatusPizza(id, status);
      return ResponseEntity.ok(updatedOrder.getStatus().toString());
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
  }

}
