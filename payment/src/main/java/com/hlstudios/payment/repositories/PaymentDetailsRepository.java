package com.hlstudios.payment.repositories;

import com.hlstudios.payment.entities.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, UUID> {

    @Modifying
    @Query(value = "update PaymentDetails payment set payment.paymentStatus = :paymentStatus where payment.orderId = :orderId")
    int updatePayment(
            @Param("orderId") UUID orderId,
            @Param("paymentStatus") String paymentStatus
    );
}
