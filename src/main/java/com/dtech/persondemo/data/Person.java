package com.dtech.persondemo.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity(name = "person")
@NoArgsConstructor
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn
  private Set<Phone> phones = new HashSet<>();

}
