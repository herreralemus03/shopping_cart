package com.hlstudios.products.dto;

import com.hlstudios.products.models.ProductFakeStore;
import com.hlstudios.products.utils.HashUUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    UUID id;
    Long apiId;
    String productName;
    String productDescription;
    String weightUnit;
    String category;
    String image;
    Float weight;
    Float price;

    public ProductDto(ProductFakeStore productFakeStore){
        this.id = HashUUID.v5(productFakeStore.getId().toString());
        this.category = productFakeStore.getCategory();
        this.image = productFakeStore.getImage();
        this.apiId = productFakeStore.getId();
        this.productName = productFakeStore.getTitle();
        this.productDescription = productFakeStore.getDescription();
        this.weightUnit = "LB";
        this.weight = 0.00f;
        this.price = productFakeStore.getPrice();
    }
}
