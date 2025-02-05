package com.PizzaJB.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PizzaJB.dto.*;

@Service
public interface UserService {

  public ResponseEntity<?> createUser(UserDto userDto);

  public ResponseEntity<UserResponseDto> findUserByEmail(String email);

}
