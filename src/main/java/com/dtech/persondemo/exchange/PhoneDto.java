package com.dtech.persondemo.exchange;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"number"})
public class PhoneDto {

    private Long id;
    private String number;
    private String type;
}
