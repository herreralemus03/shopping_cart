package com.hlstudios.orders.entites;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "shipping_articles", schema = "shopping")
public class ShippingArticle {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "id")
    private UUID id;

    @Getter @Setter
    @Column(name = "article_notes")
    private String articleNotes;

    @Getter @Setter
    @Column(name = "amount")
    private Long amount;

    @Getter @Setter
    @Column(name = "product_data")
    private String productData;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_shipping", referencedColumnName = "id")
    private OrderShipping shipping;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;
}
