package com.PizzaJB.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.PizzaJB.dto.UserDto;
import com.PizzaJB.dto.UserResponseDto;
import com.PizzaJB.entity.UserModel;
import com.PizzaJB.enums.RoleEnum;
import com.PizzaJB.repository.UserRepository;
import com.PizzaJB.services.UserService;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public ResponseEntity<?> createUser(UserDto userDto) {
    UserModel existeUser = userRepository.findByEmail(userDto.email());
    if (existeUser != null) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro: email já cadastrado");
    }

    var userPassword = passwordEncoder.encode(userDto.password());
    RoleEnum role = RoleEnum.USER;
    UserModel user = new UserModel(userDto.name(), userDto.email(), userPassword, role);
    UserModel newUser = userRepository.save(user);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body("User CREATED");
  }

  public ResponseEntity<UserResponseDto> findUserByEmail(String email) {
    UserModel user = userRepository.findByEmail(email);
    if (user != null) {
      UserResponseDto userResponseDto = new UserResponseDto(user.getId(), user.getEmail(), user.getUsername());
      return ResponseEntity.status(HttpStatus.FOUND).body(userResponseDto);
    } else {
      throw new RuntimeException("user not found with email:" + email);
    }
  }
}
