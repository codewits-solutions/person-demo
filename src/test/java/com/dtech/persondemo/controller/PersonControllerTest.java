package com.dtech.persondemo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.dtech.persondemo.exchange.PersonDto;
import com.dtech.persondemo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import org.junit.jupiter.api.Test;
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
  void sayHello() throws Exception {
    //given
//    Mockito.doReturn(new PersonDto("Hello Java"))
//        .when(personService).getMessage("001");

    // when
    URI uri = UriComponentsBuilder
        .fromPath("/say-hello")
        .queryParam("messageId", "001")
        .build().toUri();

    MockHttpServletResponse response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_VALUE)
    ).andReturn().getResponse();

    //then
    String responseStr = response.getContentAsString();
    ObjectMapper mapper = new ObjectMapper();
    PersonDto personDto = mapper.readValue(responseStr, PersonDto.class);
//    PersonDto ref = new PersonDto("Hello Java");

//    assertEquals(personDto, ref);
//    Mockito.verify(personService, Mockito.times(1)).getMessage("001");
  }
}