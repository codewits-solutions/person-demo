package com.dtech.persondemo.service;

import com.dtech.persondemo.data.Person;
import com.dtech.persondemo.data.Phone;
import com.dtech.persondemo.exchange.PersonDto;
import com.dtech.persondemo.exchange.PhoneDto;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    public Person buildPerson(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        personDto.getPhones().stream()
                .forEach(phone -> person.getPhones().add(mapPhone(person, phone)));
        return person;
    }

    public Phone mapPhone(Person person, PhoneDto phone) {
        return Phone.builder()
                .id(phone.getId())
                .number(phone.getNumber())
                .type(phone.getType())
                .person(person)
                .build();
    }

    public PersonDto buildPerson(Person personDto) {
        PersonDto person = new PersonDto();
        person.setId(personDto.getId());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        personDto.getPhones().stream()
                .forEach(phone -> person.getPhones().add(mapPhone(phone)));
        return person;
    }

    public PhoneDto mapPhone(Phone phone) {
        return PhoneDto.builder()
                .id(phone.getId())
                .number(phone.getNumber())
                .type(phone.getType())
                .build();
    }
}
