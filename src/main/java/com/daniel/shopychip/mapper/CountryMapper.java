package com.daniel.shopychip.mapper;

import com.daniel.shopychip.dto.CountryDTO;
import com.daniel.shopychip.model.Country;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
    public CountryDTO toDTO(Country country) {
        return CountryDTO.builder()
                .code(country.getCode())
                .name(country.getName())
                .build();
    }
}