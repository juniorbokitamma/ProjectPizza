package com.PizzaJB.dto;

import com.PizzaJB.enums.RoleEnum;

public record UserDto(String name, String email, String password, RoleEnum role) {
}
