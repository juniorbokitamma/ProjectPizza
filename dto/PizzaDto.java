package com.PizzaJB.dto;

import java.math.BigDecimal;

public record PizzaDto(
    Long id,
    String name,
    String description,
    BigDecimal price) {
}
