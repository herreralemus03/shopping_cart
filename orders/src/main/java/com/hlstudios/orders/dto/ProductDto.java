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
public class ProductDto {
    UUID id;
    String productName;
    String productDescription;
    String weightUnit;
    Float weight;
    Float price;
}
