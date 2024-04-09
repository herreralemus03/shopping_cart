package com.hlstudios.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderShippingDto {
    UUID id;
    String notes;
    ClientDto client;
    List<ShippingArticleDto> articles;
}
