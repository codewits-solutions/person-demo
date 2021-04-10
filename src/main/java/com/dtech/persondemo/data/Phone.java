package com.dtech.persondemo.data;

import lombok.*;

import javax.persistence.*;

@Data
@Entity(name = "phone")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"number"})
public class Phone {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column
  private String number;

  @Column
  private String type;

  @ManyToOne
  private Person person;
}
