package com.hlstudios.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {
    private UUID id;
    private String countryName;
    private String countryCode;
    private String countryAlias;
}
