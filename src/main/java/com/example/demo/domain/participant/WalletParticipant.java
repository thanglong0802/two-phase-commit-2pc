package com.example.demo.domain.participant;

import com.example.demo.entity.Wallet;
import com.example.demo.service.WalletService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class WalletParticipant implements Participant {

    private final WalletService walletService;

    private Wallet walletSnapshot;
    private Wallet workingCopy;

    private String userId;
    private Double amount;

    public WalletParticipant(
        WalletService walletService
    ) {
        this.walletService = walletService;
    }

    public void init(String userId, Double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    @Override
    public boolean prepare() {
        this.walletSnapshot = this.walletService.getWalletByUserId(this.userId);
        this.workingCopy = this.walletSnapshot.copyForUpdate(); // Create a working copy for modifications

        if (this.amount <= 0) {
            return false;
        }

        workingCopy.addBalance(this.amount);
        return true;
    }

    @Override
    public void commit() {
        this.walletService.save(this.workingCopy);
    }

    @Override
    public void rollback() {
        // No changes were made to the original walletSnapshot, so no action is needed.
    }

}
