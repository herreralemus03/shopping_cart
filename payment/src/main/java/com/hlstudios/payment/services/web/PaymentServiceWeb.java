package com.hlstudios.payment.services.web;

import com.hlstudios.payment.dto.PaymentDetailsDTO;

public interface PaymentServiceWeb {

    PaymentDetailsDTO updateOrderPaymentStatus(String orderId, String paymentStatus) throws Exception;

}
