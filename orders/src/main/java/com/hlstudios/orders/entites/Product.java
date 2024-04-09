package com.hlstudios.orders.entites;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products", schema = "shopping")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Getter @Setter
    UUID id;

    @Getter @Setter
    @Column(name = "product_name")
    String productName;

    @Getter @Setter
    @Column(name = "product_description")
    String productDescription;

    @Getter @Setter
    @Column(name = "weight_unit")
    String weightUnit;

    @Getter @Setter
    @Column(name = "weight")
    Float weight;

    @Getter @Setter
    @Column(name = "price")
    Float price;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_store", referencedColumnName = "id")
    Store store;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_product_sub_category", referencedColumnName = "id")
    ProductSubCategory productSubCategory;

}
