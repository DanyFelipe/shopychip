package com.daniel.shopychip.service;

import com.daniel.shopychip.dto.CountryDTO;
import com.daniel.shopychip.mapper.CountryMapper;
import com.daniel.shopychip.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public List<CountryDTO> getAllCountries() {
        return countryRepository.findAll()
                .stream()
                .map(countryMapper::toDTO)
                .toList();
    }
}