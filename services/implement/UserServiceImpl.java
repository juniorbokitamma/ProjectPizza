package com.PizzaJB.services.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.PizzaJB.dto.UserDto;
import com.PizzaJB.entity.UsersModel;
import com.PizzaJB.repository.UserRepository;
import com.PizzaJB.services.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public ResponseEntity<?> createUser(UserDto userDto) {
    UsersModel existUser = userRepository.findByEmail(userDto.email());
    if (existUser != null) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Err: already registed");
    }
    var password = passwordEncoder.encode(userDto.password());
    UsersModel User = new UsersModel(userDto.name(), userDto.email(), password);
    UsersModel newUser = userRepository.save(User);
    return ResponseEntity.status(HttpStatus.CREATED).body("User created");

  }

}
