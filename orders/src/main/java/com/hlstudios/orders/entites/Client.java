package com.hlstudios.orders.entites;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "clients", schema = "shopping")
public class Client {

    @Id
    @GeneratedValue
    @Getter @Setter
    @Column(name = "id")
    UUID id;

    @Getter @Setter
    @Column(name = "client_name")
    String clientName;

    @Getter @Setter
    @Column(name = "client_lastname")
    String clientLastName;

    @Getter @Setter
    @Column(name = "client_address")
    String clientAddress;

}
