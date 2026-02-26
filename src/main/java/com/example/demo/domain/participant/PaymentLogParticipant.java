package com.example.demo.domain.participant;

import com.example.demo.service.PaymentLogService;
import com.example.demo.entity.PaymentLog;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class PaymentLogParticipant implements Participant {

    private final PaymentLogService paymentLogService;

    private PaymentLog pendingPaymentLog;

    private String userId;
    private Double amount;

    public PaymentLogParticipant(
        PaymentLogService paymentLogService
    ) {
        this.paymentLogService = paymentLogService;
    }

    public void init(String userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    @Override
    public boolean prepare() {
        if (this.amount > 1_000_000) {
            return false;
        }

        this.pendingPaymentLog = new PaymentLog(0, 0L, this.userId, this.amount, "SUCCESS");
        return true;
    }

    @Override
    public void commit() {
        this.paymentLogService.save(this.pendingPaymentLog);
    }

    @Override
    public void rollback() {
        // In case of rollback, we can log a failed payment attempt or simply discard the pending log.
        this.pendingPaymentLog = new PaymentLog(0, 0L, this.userId, this.amount, "FAILED");
        this.commit();
    }
}
