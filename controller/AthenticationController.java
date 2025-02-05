package com.PizzaJB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.PizzaJB.dto.AuthDto;
import com.PizzaJB.dto.JwtResponseDto;
import com.PizzaJB.dto.UserDto;
import com.PizzaJB.services.AuthenticationService;
import com.PizzaJB.services.UserService;

import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AthenticationController {

  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @CrossOrigin
  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> login(@RequestBody AuthDto authDto) {

    try {
      var userAuthentication = new UsernamePasswordAuthenticationToken(authDto.email(), authDto.password());

      authenticationManager.authenticate(userAuthentication);

      String token = authenticationService.getTokenJwt(authDto);
      return ResponseEntity.ok(new JwtResponseDto(token));
    } catch (AuthenticationException e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> register(@RequestBody UserDto userDto) {
    return userService.createUser(userDto);

  }

}
