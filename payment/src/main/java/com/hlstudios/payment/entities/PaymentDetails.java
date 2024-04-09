package com.hlstudios.payment.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "orders", schema = "shopping")
public class PaymentDetails {

    @Id
    @Column(name = "id")
    @Getter @Setter
    UUID orderId;

    @Column(name = "payment_status")
    @Getter @Setter
    String paymentStatus;

}
