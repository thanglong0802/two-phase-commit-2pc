package com.example.demo.service;

import com.example.demo.entity.Wallet;
import com.example.demo.repository.WalletRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class WalletService {

    private static final Logger log = LoggerFactory.getLogger(WalletService.class);

    private final WalletRepository walletRepository;

    public WalletService(
        WalletRepository walletRepository
    ) {
        this.walletRepository = walletRepository;
    }

    public Wallet getWalletByUserId(String userId) {
        log.info("Fetching wallet for userId: {}", userId);
        Optional<Wallet> wallet = this.walletRepository.findWalletByUserId(userId);
        if (wallet.isEmpty()) {
            throw new RuntimeException("Wallet not found for userId: " + userId);
        }

        return wallet.get();
    }

    @Transactional
    public Wallet save(Wallet wallet) {
        log.info("Saving wallet for userId: {}", wallet.getUserId());
        return this.walletRepository.save(wallet);
    }
}
