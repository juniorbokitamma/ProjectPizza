package com.PizzaJB.dto;

import java.math.BigDecimal;
import java.util.List;

import com.PizzaJB.enums.PizzaStatusEnum;

public record OrderResponseDto(
    Long id,
    List<PizzaDto> pizzas,
    PizzaStatusEnum status,
    BigDecimal totalPrice,
    UserResponseDto user) {

}
