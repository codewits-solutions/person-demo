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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String number;

  @Column
  private String type;

  @OneToOne(optional = true)
  private Person person;
}
