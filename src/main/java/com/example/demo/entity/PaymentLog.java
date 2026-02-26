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
@Entity(name = "payment_log")
@SQLRestriction(value = "is_deleted = 0")
@ToString(callSuper = true)
public class PaymentLog extends AbstractAuditableEntity<String> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    private String id;

    private String userId;

    private Double amount;

    private String status;

    public PaymentLog() {}

    public PaymentLog(Integer isDeleted, Long version, String userId, Double amount, String status) {
        super(isDeleted, version);
        this.userId = userId;
        this.amount = amount;
        this.status = status;
    }

    public PaymentLog userId(String userId) {
        this.userId = userId;
        return this;
    }

    public PaymentLog amount(Double amount) {
        this.amount = amount;
        return this;
    }

    public PaymentLog status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}
