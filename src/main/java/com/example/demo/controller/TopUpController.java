package com.example.demo.controller;

import com.example.demo.domain.coordinator.TwoPhaseCommitCoordinator;
import com.example.demo.domain.participant.PaymentLogParticipant;
import com.example.demo.domain.participant.WalletParticipant;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/top-up")
public class TopUpController {

    private final WalletParticipant walletParticipant;
    private final PaymentLogParticipant paymentLogParticipant;
    private final TwoPhaseCommitCoordinator coordinator;

    public TopUpController(
        WalletParticipant walletParticipant,
        PaymentLogParticipant paymentLogParticipant,
        TwoPhaseCommitCoordinator coordinator
    ) {
        this.walletParticipant = walletParticipant;
        this.paymentLogParticipant = paymentLogParticipant;
        this.coordinator = coordinator;
    }

    @PostMapping
    public String topUpWallet(@RequestParam String userId, @RequestParam Double amount) {
        walletParticipant.init(userId, amount);
        paymentLogParticipant.init(userId, amount);

        this.coordinator.execute(List.of(walletParticipant, paymentLogParticipant));
        return "Top-up successful";
    }
}
