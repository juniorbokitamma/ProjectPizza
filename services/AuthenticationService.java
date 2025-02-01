package com.PizzaJB.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.PizzaJB.dto.AuthDto;

public interface AuthenticationService extends UserDetailsService {

  public String getTokenJwt(AuthDto authDto);

  public String validateToken(String token);

}
