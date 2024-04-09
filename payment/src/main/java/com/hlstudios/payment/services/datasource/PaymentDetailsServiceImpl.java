package com.hlstudios.payment.services.datasource;

import com.hlstudios.payment.entities.PaymentDetails;
import com.hlstudios.payment.repositories.PaymentDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.UUID;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    private final PaymentDetailsRepository paymentDetailsRepository;

    public PaymentDetailsServiceImpl(PaymentDetailsRepository paymentDetailsRepository) {
        this.paymentDetailsRepository = paymentDetailsRepository;
    }

    @Override
    @Transactional(rollbackFor = SQLException.class)
    public PaymentDetails updatePayment(UUID orderId, String paymentStatus) throws Exception {
        int entitiesModified = paymentDetailsRepository.updatePayment(orderId, paymentStatus);
        if(entitiesModified > 0){
            return paymentDetailsRepository.findById(orderId).orElse(null);
        }
        return null;
    }

}
