package com.hlstudios.orders.entites;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "product_sub_categories")
public class ProductSubCategory {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "id")
    UUID id;

    @Getter @Setter
    @Column(name = "sub_category_name")
    String subCategoryName;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_product_category", referencedColumnName = "id")
    ProductCategory productCategory;

}
