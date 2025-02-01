package com.PizzaJB.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PizzaJB.dto.UserDto;

@Service
public interface UserService {
  public ResponseEntity<?> createUser(UserDto userDto);

}
