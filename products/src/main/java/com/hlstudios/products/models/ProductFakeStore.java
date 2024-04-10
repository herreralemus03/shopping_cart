package com.hlstudios.products.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductFakeStore {

    @JsonAlias({"id"})
    Long id;
    @JsonAlias({"price"})
    Float price;
    @JsonAlias({"title"})
    String title;
    @JsonAlias({"description"})
    String description;
    @JsonAlias({"image"})
    String image;
    @JsonAlias({"category"})
    String category;

}
