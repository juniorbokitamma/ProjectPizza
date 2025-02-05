package com.PizzaJB.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.PizzaJB.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "usuarios")
@Entity
public class UserModel implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @JsonIgnore
  @Column(nullable = false)
  private String password;

  @JsonIgnore
  @Column(nullable = false)
  private RoleEnum role = RoleEnum.USER;

  @JsonIgnore
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private List<OrderModel> orders;

  public List<OrderModel> getOrders() {
    return orders;
  }

  public UserModel() {

  }

  public UserModel(String name, String email, String password, RoleEnum role) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;

  }

  public UserModel(Long id, String name, String email) {
    this.name = name;
    this.email = email;
    this.id = id;

  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (this.role == RoleEnum.ADMIN) {
      return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
          new SimpleGrantedAuthority("ROLE_USER"));
    }
    return List.of(
        new SimpleGrantedAuthority("ROLE_USER")

    );
  }

  public RoleEnum getRole() {
    return role;
  }

  @Override
  public String getPassword() {
    return this.password;
  }

  @Override
  public String getUsername() {
    return this.email;
  }

  public String getEmail() {
    return email;
  }

  public Long getId() {
    return id;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRole(RoleEnum role) {
    this.role = role;
  }

  public void setOrders(List<OrderModel> orders) {
    this.orders = orders;
  }

}
