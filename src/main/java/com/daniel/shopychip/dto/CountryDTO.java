package com.daniel.shopychip.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryDTO {
    private String code;
    private String name;
}