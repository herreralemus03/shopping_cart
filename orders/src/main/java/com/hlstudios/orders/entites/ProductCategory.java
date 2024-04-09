package com.hlstudios.orders.entites;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "id")
    UUID id;

    @Getter @Setter
    @Column(name = "category_name")
    String categoryName;

}
