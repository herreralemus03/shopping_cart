package com.hlstudios.orders.entites;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order_shippings", schema = "shopping")
public class OrderShipping {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "id")
    private UUID id;

    @Getter @Setter
    @Column(name = "notes")
    private String notes;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_client", referencedColumnName = "id")
    private Client client;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_order", referencedColumnName = "id")
    private Order order;

    @Getter @Setter
    @OneToMany(mappedBy = "shipping")
    private List<ShippingArticle> articles;

}
