package com.dtech.persondemo.exchange;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class PersonDto {
  private Long id;
  private String firstName;
  private String lastName;
  private Set<PhoneDto> phones = new HashSet<>();
}
