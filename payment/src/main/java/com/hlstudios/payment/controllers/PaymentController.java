package com.hlstudios.payment.controllers;

import com.hlstudios.payment.dto.PaymentDetailsDTO;
import com.hlstudios.payment.services.web.PaymentServiceWeb;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(path = "/api/v1/payments")
public class PaymentController {

    final PaymentServiceWeb paymentServiceWeb;

    public PaymentController(PaymentServiceWeb paymentServiceWeb) {
        this.paymentServiceWeb = paymentServiceWeb;
    }

    @GetMapping(path = "/update/{order_id}/{payment_status}")
    public ResponseEntity<?> updatePaymentStatus(
            @PathVariable("order_id") String orderId,
            @PathVariable("payment_status") String paymentStatus
    ) {
       try {
            PaymentDetailsDTO paymentDetails = paymentServiceWeb.updateOrderPaymentStatus(orderId, paymentStatus);
            return ResponseEntity.ok(new HashMap<String, Object>(){{
                put("message", "ok");
                put("body", paymentDetails);
            }});
       } catch (Exception e) {
           e.printStackTrace();
            return ResponseEntity.status(500).body(e);
       }
    }
}
