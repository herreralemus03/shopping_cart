package com.hlstudios.orders.services.web.country;

import com.hlstudios.orders.dto.CountryDto;
import org.springframework.data.domain.Page;

public interface CountriesServiceWeb {
    Page<CountryDto> pageCountries(Integer page, Integer size);

}
