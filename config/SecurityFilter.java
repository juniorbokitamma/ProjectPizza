package com.PizzaJB.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.PizzaJB.entity.UsersModel;
import com.PizzaJB.repository.UserRepository;
import com.PizzaJB.services.AuthenticationService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private AuthenticationService authenticationService;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String token = extraitToken(request);
    if (token != null) {
      String email = authenticationService.validateToken(token);
      UsersModel userModel = userRepository.findByEmail(email);

      var authentication = new UsernamePasswordAuthenticationToken(userModel, null, userModel.getAuthorities());

      SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    filterChain.doFilter(request, response);
  }

  public String extraitToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null) {
      return null;
    }

    if (!authHeader.split(" ")[0].equals("Bearer")) {
      return null;
    }
    return authHeader.split(" ")[1];
  }
}
