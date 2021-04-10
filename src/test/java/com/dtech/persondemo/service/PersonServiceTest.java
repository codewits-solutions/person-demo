package com.dtech.persondemo.service;

import com.dtech.persondemo.data.Person;
import com.dtech.persondemo.data.Phone;
import com.dtech.persondemo.exchange.PersonDto;
import com.dtech.persondemo.exchange.PhoneDto;
import com.dtech.persondemo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @Spy
  private PersonMapper personMapper = new PersonMapper();

  @InjectMocks
  private PersonService personService;

  @Test
  void savePerson() {
    Person person = getPerson();
    Mockito.doReturn(person)
        .when(personRepository).save(ArgumentMatchers.argThat(
                p -> p.getFirstName().equals(person.getFirstName())));
    Long personId = personService.savePerson(getPersonDto()).getId();
    assertEquals(personId, 1);

  }

  @Test
  void findPersonById() {
    Person person = getPerson();
    Mockito.doReturn(Optional.of(person))
            .when(personRepository).findById(1L);
    PersonDto personDto = personService.findPersonById(1L);
    assertEquals(personDto.getId(), 1);
    assertEquals(personDto.getFirstName(), "Anand");
    assertEquals(personDto.getLastName(), "Vaidya");
    assertTrue(personDto.getPhones().contains(PhoneDto.builder().number("1234").build()));
  }

  @Test
  void findAll() {
    Person person = getPerson();
    Mockito.doReturn(Collections.singletonList(person))
            .when(personRepository).findAll();
    PersonDto personDto = personService.getAll().get(0);
    assertEquals(personDto.getId(), 1);
    assertEquals(personDto.getFirstName(), "Anand");
    assertEquals(personDto.getLastName(), "Vaidya");
    assertTrue(personDto.getPhones().contains(PhoneDto.builder().number("1234").build()));
  }

  @Test
  void deleteById() {
    personService.deleteById(1L);
    Mockito.verify(personRepository, Mockito.times(1)).deleteById(1L);
  }

  @Test
  void findPersonByIdShouldReturnNullWhenNoMatch() {
    Mockito.doReturn(Optional.empty())
            .when(personRepository).findById(1L);
    PersonDto personDto = personService.findPersonById(1L);
    assertNull(personDto);
  }

  private PersonDto getPersonDto() {
    PersonDto person = new PersonDto();
    person.setFirstName("Anand");
    person.setLastName("Vaidya");
    Set<PhoneDto> phones = Stream.of(new PhoneDto(null, "1234", "work")).collect(Collectors.toSet());
    person.setPhones(phones);
    return person;
  }

  private Person getPerson() {
    Person person = new Person();
    person.setId(1L);
    person.setFirstName("Anand");
    person.setLastName("Vaidya");
    Set<Phone> phones = Stream.of(Phone.builder().number("1234").build()).collect(Collectors.toSet());
    person.setPhones(phones);
    return person;
  }


}