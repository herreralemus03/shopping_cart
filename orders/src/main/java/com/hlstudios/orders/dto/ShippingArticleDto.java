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
public class ShippingArticleDto {
    UUID id;
    String articleNotes;
    Long amount;
    ProductDto product;
}
