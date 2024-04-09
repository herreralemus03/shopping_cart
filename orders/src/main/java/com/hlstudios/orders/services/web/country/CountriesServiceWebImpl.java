package com.hlstudios.orders.services.web.country;

import com.hlstudios.orders.dto.CountryDto;
import com.hlstudios.orders.services.datasource.country.CountriesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CountriesServiceWebImpl implements CountriesServiceWeb {

    final CountriesService countriesService;
    final ModelMapper modelMapper;

    public CountriesServiceWebImpl(
            CountriesService countriesService,
            ModelMapper modelMapper
    ) {
        this.countriesService = countriesService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<CountryDto> pageCountries(Integer page, Integer size) {
        return countriesService.findAllPaged(PageRequest.of(page, size))
                .map(country -> modelMapper.map(country, CountryDto.class));
    }
}
