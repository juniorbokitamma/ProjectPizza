package com.PizzaJB.services.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.PizzaJB.dto.PizzaDto;
import com.PizzaJB.entity.PizzaModel;
import com.PizzaJB.enums.PizzaStatusEnum;
import com.PizzaJB.repository.PizzaRespository;
import com.PizzaJB.services.PizzaService;

@Service
public class PizzaServiceImp implements PizzaService {

  @Autowired
  private PizzaRespository pizzaRespository;

  @Override
  public ResponseEntity<?> createPizza(PizzaDto pizzaDto) {
    PizzaModel pizza = new PizzaModel();
    pizza.setName(pizzaDto.name());
    pizza.setDescription(pizzaDto.description());
    pizza.setPrice(pizzaDto.price());

    pizzaRespository.save(pizza);

    return ResponseEntity.status(HttpStatus.CREATED).body("Pizza criada com sucesso");

  }

  @Override
  public List<PizzaModel> getAllPizzas() {
    return pizzaRespository.findAll();
  }

  @Override
  public List<PizzaModel> findByIdIn(List<Long> ids) {
    return pizzaRespository.findAllById(ids);
  }

  @Override
  public PizzaModel updatePizza(Long id, PizzaDto pizzaDto) {
    return pizzaRespository.findById(id).map(pizza -> {
      pizza.setName(pizzaDto.name());
      pizza.setDescription(pizzaDto.description());
      pizza.setPrice(pizzaDto.price());
      return pizzaRespository.save(pizza);
    }).orElseThrow(() -> new RuntimeException("Pizza not found"));
  }

}
