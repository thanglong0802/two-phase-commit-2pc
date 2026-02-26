package com.example.demo.service;

import com.example.demo.entity.PaymentLog;
import com.example.demo.repository.PaymentLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentLogService {

    private final PaymentLogRepository paymentLogRepository;

    public PaymentLogService(
        PaymentLogRepository paymentLogRepository
    ) {
        this.paymentLogRepository = paymentLogRepository;
    }

    @Transactional
    public PaymentLog save(PaymentLog paymentLog) {
        return this.paymentLogRepository.save(paymentLog);
    }
}
