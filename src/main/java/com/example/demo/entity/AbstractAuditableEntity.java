package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.hibernate.annotations.SQLRestriction;

import java.io.Serializable;

@MappedSuperclass
@SQLRestriction(value = "is_deleted = 0")
public abstract class AbstractAuditableEntity<T> extends AbstractAuditingEntity<T> implements Serializable {

    @Column(name = "is_deleted")
    protected Integer isDeleted;

    @Version
    @Column(name = "version")
    protected Long version;

    protected AbstractAuditableEntity() {}

    protected AbstractAuditableEntity(Integer isDeleted, Long version) {
        this.isDeleted = isDeleted;
        this.version = version;
    }

    public AbstractAuditableEntity<T> isDeleted(Integer isDeleted) {
        setIsDeleted(isDeleted);
        return this;
    }

    public AbstractAuditableEntity<T> version(Long version) {
        setVersion(version);
        return this;
    }

    /*public AbstractAuditingEntity<T> createdBy(String createdBy) {
        setCreatedBy(createdBy);
        return this;
    }

    public AbstractAuditingEntity<T> createdDate(Instant createdDate) {
        setCreatedDate(createdDate);
        return this;
    }

    public AbstractAuditingEntity<T> lastModifiedBy(String lastModifiedBy) {
        setLastModifiedBy(lastModifiedBy);
        return this;
    }

    public AbstractAuditingEntity<T> lastModifiedDate(Instant lastModifiedDate) {
        setLastModifiedDate(lastModifiedDate);
        return this;
    }*/

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
