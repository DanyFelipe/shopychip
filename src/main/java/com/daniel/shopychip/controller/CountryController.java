package com.daniel.shopychip.controller;

import com.daniel.shopychip.dto.CountryDTO;
import com.daniel.shopychip.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public List<CountryDTO> getCountries() {
        return countryService.getAllCountries();
    }
}