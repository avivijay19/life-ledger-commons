package com.github.avivijay19.lifeledger.commons.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;

/**
 * @author : avinashvijayvargiya
 * @created : 15/12/25, Monday
 **/

@MappedSuperclass
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AuditableEntity {
    @CreationTimestamp
    @Column(name = "audit_create_timestamp", updatable = false, nullable = false)
    protected Instant auditCreateTimestamp;

    @UpdateTimestamp
    @Column(name = "audit_update_timestamp")
    protected Instant auditUpdateTimestamp;
}
