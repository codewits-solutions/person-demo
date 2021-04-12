package com.dtech.persondemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.dtech.persondemo.exchange.PersonDto;
import com.dtech.persondemo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.UriComponentsBuilder;


@AutoConfigureMockMvc
@SpringBootTest
class PersonControllerTest {

  @Autowired
  private MockMvc mvc;

  @MockBean
  private PersonService personService;

  @Test
  void findOne() throws Exception {
    //given
    Mockito.doReturn(new PersonDto())
        .when(personService).findPersonById(1);

    // when
    URI uri = UriComponentsBuilder
        .fromPath("/person/1")
        .build().toUri();

    MockHttpServletResponse response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_VALUE)
    ).andReturn().getResponse();

    //then
    String responseStr = response.getContentAsString();
    ObjectMapper mapper = new ObjectMapper();
    PersonDto personDto = mapper.readValue(responseStr, PersonDto.class);
    PersonDto ref = new PersonDto();

    assertEquals(personDto, ref);
    Mockito.verify(personService, Mockito.times(1)).findPersonById(1);
  }
}