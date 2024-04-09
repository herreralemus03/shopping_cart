package com.hlstudios.orders.services.datasource.country;

import com.hlstudios.orders.entites.Country;
import com.hlstudios.orders.repositories.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class CountriesServiceImpl implements CountriesService {

    final CountriesRepository countriesRepository;

    public CountriesServiceImpl(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    @Override
    public List<Country> findAllListed() {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Page<Country> findAllPaged(Pageable pageable) {
        return countriesRepository.findAll(pageable);
    }

    @Override
    public Country findById(UUID id) {
        return null;
    }

    @Override
    public Country add(Country entity) {
        return null;
    }

    @Override
    public Country update(Country entity) {
        return null;
    }

    @Override
    public Country delete(Country entity) {
        return null;
    }
}
