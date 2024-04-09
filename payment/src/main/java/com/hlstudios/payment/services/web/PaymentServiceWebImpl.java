package com.hlstudios.payment.services.web;

import com.hlstudios.payment.dto.PaymentDetailsDTO;
import com.hlstudios.payment.entities.PaymentDetails;
import com.hlstudios.payment.services.datasource.PaymentDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentServiceWebImpl implements PaymentServiceWeb {

    final PaymentDetailsService paymentDetailsService;
    final ModelMapper mapper;

    public PaymentServiceWebImpl(PaymentDetailsService paymentDetailsService, ModelMapper mapper) {
        this.paymentDetailsService = paymentDetailsService;
        this.mapper = mapper;
    }

    @Override
    public PaymentDetailsDTO updateOrderPaymentStatus(String orderId, String paymentStatus) throws Exception {
        PaymentDetails paymentDetails = paymentDetailsService.updatePayment(UUID.fromString(orderId), paymentStatus);
        return mapper.map(paymentDetails, PaymentDetailsDTO.class);
    }

}
