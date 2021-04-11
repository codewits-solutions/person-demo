package com.dtech.persondemo.service;

import com.dtech.persondemo.data.Person;
import com.dtech.persondemo.exchange.PersonDto;
import com.dtech.persondemo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonDto savePerson(PersonDto personDto) {
        Person person = personMapper.buildPerson(personDto);
        return personMapper.buildPerson(personRepository.save(person));

    }

    public PersonDto findPersonById(long id) {
        return personRepository.findById(id)
                .map(personMapper::buildPerson)
                .orElse(null);
    }

    public List<PersonDto> getAll() {
        return personRepository.findAll().stream()
                .map(personMapper::buildPerson)
                .collect(Collectors.toList());
    }

    public void deleteById(long id) {
        personRepository.deleteById(id);
    }
}
