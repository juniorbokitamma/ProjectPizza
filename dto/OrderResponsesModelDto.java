package com.PizzaJB.dto;

import java.math.BigDecimal;
import java.util.List;

import com.PizzaJB.entity.PizzaModel;
import com.PizzaJB.enums.PizzaStatusEnum;

public record OrderResponsesModelDto(
    Long id,
    List<PizzaModel> pizzas,
    PizzaStatusEnum status,
    BigDecimal totalPrice,
    UserResponseDto user) {
}
