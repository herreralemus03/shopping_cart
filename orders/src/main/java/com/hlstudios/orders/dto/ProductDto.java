package com.hlstudios.orders.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    String id;
    Long apiId;
    String productName;
    String productDescription;
    String weightUnit;
    Float weight;
    Float price;
    String image;
    String category;
}
