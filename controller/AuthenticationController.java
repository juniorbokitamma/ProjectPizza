package com.PizzaJB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.PizzaJB.dto.AuthDto;
import com.PizzaJB.dto.UserDto;
import com.PizzaJB.services.AuthenticationService;
import com.PizzaJB.services.UserService;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
  @Autowired
  private AuthenticationService authenticationService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private UserService userService;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public String login(@RequestBody AuthDto authDto) {
    var userAuthentication = new UsernamePasswordAuthenticationToken(authDto.email(), authDto.password());
    authenticationManager.authenticate(userAuthentication);
    return authenticationService.getTokenJwt(authDto);
  }

  @PostMapping("register")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> register(@RequestBody UserDto userDto) {
    return userService.createUser(userDto);
  }

}
