package com.dtech.persondemo.controller;

import com.dtech.persondemo.exchange.PersonDto;
import com.dtech.persondemo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/person")
public class PersonController {

  private final PersonService personService;

  @PostMapping
  public PersonDto savePerson(@RequestBody PersonDto personDto) {
    return personService.savePerson(personDto);

  }

  @GetMapping("/{id}")
  public PersonDto findPersonById(@PathVariable Long id) {
    return personService.findPersonById(id);
  }

  @GetMapping
  public List<PersonDto> getAll() {
    return personService.getAll();
  }

  @DeleteMapping("/{id}")
  public void deleteById(@PathVariable Long id) {
    personService.deleteById(id);
  }

}
