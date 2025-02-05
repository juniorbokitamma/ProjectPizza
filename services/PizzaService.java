package com.PizzaJB.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PizzaJB.dto.PizzaDto;
import com.PizzaJB.entity.PizzaModel;

@Service
public interface PizzaService {
  public ResponseEntity<?> createPizza(PizzaDto pizzaDto);

  List<PizzaModel> findByIdIn(List<Long> ids);

  public PizzaModel updatePizza(Long id, PizzaDto pizzaDto);

  public List<PizzaModel> getAllPizzas();

}
