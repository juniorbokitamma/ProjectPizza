package com.PizzaJB.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("teste")
public class userControllerTeste {

  @GetMapping
  public String hello() {
    return "oooooii";
  }

}
