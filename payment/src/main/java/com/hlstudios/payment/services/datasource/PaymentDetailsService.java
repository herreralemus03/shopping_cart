package com.hlstudios.payment.services.datasource;

import com.hlstudios.payment.entities.PaymentDetails;
import java.util.UUID;

public interface PaymentDetailsService {
    PaymentDetails updatePayment(UUID orderId, String paymentStatus) throws Exception;
}
