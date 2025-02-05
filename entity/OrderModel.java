package com.PizzaJB.entity;

import java.math.BigDecimal;
import java.util.List;

import com.PizzaJB.enums.PizzaStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private UserModel user;

  @ManyToMany
  @JoinTable(name = "order_pizza", joinColumns = @JoinColumn(name = "order_pizza"), inverseJoinColumns = @JoinColumn(name = "pizza_id"))
  private List<PizzaModel> pizzas;

  @Column(name = "total_price")
  private BigDecimal totalPrice;

  private PizzaStatusEnum status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserModel getUser() {
    return user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

  public List<PizzaModel> getPizzas() {
    return pizzas;
  }

  public void setPizzas(List<PizzaModel> pizzas) {
    this.pizzas = pizzas;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public PizzaStatusEnum getStatus() {
    return status;
  }

  public void setStatus(PizzaStatusEnum status) {
    this.status = status;
  }

}
