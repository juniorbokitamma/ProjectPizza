package com.PizzaJB.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.PizzaJB.dto.PizzaDto;
import com.PizzaJB.entity.PizzaModel;
import com.PizzaJB.services.PizzaService;

@RestController
@RequestMapping("/pizza")
public class PizzaController {

  @Autowired
  private PizzaService pizzaService;

  @PostMapping
  public ResponseEntity<?> createPizza(@RequestBody PizzaDto pizzaDto) {
    return pizzaService.createPizza(pizzaDto);

  }

  @GetMapping("/")
  public List<PizzaModel> getAllPizza() {
    return pizzaService.getAllPizzas();
  }

  @PutMapping("/{id}")
  public PizzaModel updatePizza(@PathVariable Long id, @RequestBody PizzaDto pizzaDto) {
    return pizzaService.updatePizza(id, pizzaDto);

  }
}
