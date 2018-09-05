package com.yabanya.commons.orm.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Access(AccessType.FIELD)
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditedEntity extends AbstractEntity implements AuditedEntity {

    private static final long serialVersionUID = -761602548626178624L;

    @Version
    @Column(name = "C_VERSION", nullable = false, precision = 4)
    private Integer version;

    @Enumerated(EnumType.STRING)
    @Column(name = "RECORD_STATUS", length = 10)
    private RecordStatus recordStatus;

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    private String createdBy;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY", insertable = false)
    private String lastModifiedBy;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "CREATED_DATE", updatable = false, nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @Column(name = "LAST_MODIFIED_DATE", insertable = false)
    private LocalDateTime lastModifiedDate;

    protected AbstractAuditedEntity() {
        this.recordStatus = RecordStatus.ACTIVE;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    @Override
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    @Override
    public RecordStatus getRecordStatus() {
        return recordStatus;
    }

    @Override
    public void setRecordStatus(final RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }
}
