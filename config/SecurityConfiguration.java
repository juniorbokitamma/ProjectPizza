package com.PizzaJB.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired
  private SecurityFilter securityFilter;

  private final CustomCorsConfiguration customCorsConfiguration;

  @Autowired
  public SecurityConfiguration(CustomCorsConfiguration customCorsConfiguration) {
    this.customCorsConfiguration = customCorsConfiguration;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
        .cors(cors -> cors.configurationSource(customCorsConfiguration))
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(HttpMethod.POST, "/pizza").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/pizza").hasRole("ADMIN")

            .requestMatchers(HttpMethod.GET, "/pizza").permitAll()

            .requestMatchers(HttpMethod.PUT, "/orders").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/orders").permitAll()
            .requestMatchers(HttpMethod.GET, "/orders").permitAll()
            .requestMatchers(HttpMethod.DELETE, "/orders").permitAll()

            .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
            .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
            .anyRequest().authenticated())
        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }

  @Bean
  public PasswordEncoder passwordEncode() {
    return new BCryptPasswordEncoder();

  }

  @Bean
  public AuthenticationManager authenticatorManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();

  }

}
