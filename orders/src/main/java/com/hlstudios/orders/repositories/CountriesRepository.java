package com.hlstudios.orders.repositories;

import com.hlstudios.orders.entites.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CountriesRepository extends JpaRepository<Country, UUID> {
}
