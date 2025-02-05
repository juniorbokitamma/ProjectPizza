package com.PizzaJB.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PizzaJB.entity.PizzaModel;

@Repository
public interface PizzaRespository extends JpaRepository<PizzaModel, Long> {
  List<PizzaModel> findByIdIn(List<Long> ids);
}
