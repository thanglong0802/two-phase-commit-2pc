package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.ToString;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;

@Table
@Entity(name = "wallet")
@SQLRestriction(value = "is_deleted = 0")
@ToString(callSuper = true)
public class Wallet extends AbstractAuditableEntity<String> implements Serializable, Cloneable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    private String id;

    private String userId;

    private Double balance = 0.0;

    public Wallet() {}

    public Wallet(Integer isDeleted, Long version, String userId, Double balance) {
        super(isDeleted, version);
        this.userId = userId;
        this.balance = balance;
    }

    public Wallet(String id, Integer isDeleted, Long version, String userId, Double balance) {
        this(isDeleted, version, userId, balance);
        this.id = id;
    }

    @Override
    public Wallet clone() {
        try {
            Wallet clone = (Wallet) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public Wallet copyForInsert() {
        return new Wallet(0, 0L, this.userId, this.balance);
    }

    public Wallet copyForUpdate() {
        return new Wallet(this.id, super.getIsDeleted(), super.getVersion(), this.userId, this.balance);
    }

    public Wallet id(String id) {
        this.id = id;
        return this;
    }

    public Wallet userId(String userId) {
        this.userId = userId;
        return this;
    }

    public Wallet addBalance(Double balance) {
        this.balance += balance;
        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Double getBalance() {
        return balance;
    }
}
