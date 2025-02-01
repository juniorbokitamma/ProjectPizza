package com.PizzaJB.services.implement;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PizzaJB.dto.AuthDto;
import com.PizzaJB.entity.UsersModel;
import com.PizzaJB.repository.UserRepository;
import com.PizzaJB.services.AuthenticationService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class AuthenticationServiceImp implements AuthenticationService {
  private final String SECRET_KEY = "juniordossantos";

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email);
  }

  @Override
  public String getTokenJwt(AuthDto authDto) {
    UsersModel user = userRepository.findByEmail(authDto.email());
    return gerateToken(user);
  }

  public String gerateToken(UsersModel usersModel) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
      return JWT.create()
          .withIssuer("PizzaJB")
          .withSubject(usersModel.getEmail())
          .withExpiresAt(gerateDateExp())
          .sign(algorithm);
    } catch (JWTDecodeException e) {
      throw new RuntimeException("Err in gerate of token" + e.getMessage());
    }

  }

  @Override
  public String validateToken(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
      return JWT.require(algorithm)
          .withIssuer("PizzaJB")
          .build()
          .verify(token)
          .getSubject();
    } catch (JWTVerificationException e) {
      return "";
    }
  }

  private Instant gerateDateExp() {
    return LocalDateTime.now().plusHours(4).toInstant(ZoneOffset.of("-03:00"));
  }
}
