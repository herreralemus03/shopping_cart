package com.hlstudios.orders.entites;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders", schema = "shopping")
public class Order {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "id")
    UUID id;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_country", referencedColumnName = "id")
    private Country country;

    @ManyToOne
    @Getter @Setter
    @JoinColumn(name = "id_store", referencedColumnName = "id")
    private Store store;

    @Getter @Setter
    @OneToMany(mappedBy = "order")
    List<OrderShipping> orderShipping;
}
