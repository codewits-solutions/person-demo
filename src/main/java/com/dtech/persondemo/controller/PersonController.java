package com.dtech.persondemo.controller;

import com.dtech.persondemo.exchange.PersonDto;
import com.dtech.persondemo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

  private final PersonService personService;

  @GetMapping("/say-hello")
  public PersonDto sayHello(@RequestParam String messageId) {
    return null;
  }

}
