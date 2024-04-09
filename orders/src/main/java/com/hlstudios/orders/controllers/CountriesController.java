package com.hlstudios.orders.controllers;

import com.hlstudios.orders.services.web.country.CountriesServiceWeb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api/v1/countries")
public class CountriesController {

    final CountriesServiceWeb countriesServiceWeb;

    public CountriesController(
            CountriesServiceWeb countriesServiceWeb
    ){
        this.countriesServiceWeb = countriesServiceWeb;
    }

    @GetMapping(path = "/get/paged")
    public ResponseEntity<?> getCountriesPaged(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "25") Integer size
    ){
        try {
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("code", 200);
                put("message", "ok");
                put("body", countriesServiceWeb.pageCountries(page, size));
            }});
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body(e);
        }
    }
}
