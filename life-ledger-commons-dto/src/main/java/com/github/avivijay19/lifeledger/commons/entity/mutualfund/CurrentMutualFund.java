package com.github.avivijay19.lifeledger.commons.entity.mutualfund;

import com.github.avivijay19.lifeledger.commons.embeddedId.mutualfund.CurrentMutualFundSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.Instant;

/**
 * @author : avinashvijayvargiya
 * @created : 27/12/24, Friday
 **/

@Table(name = "current_mutual_fund")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrentMutualFund {

    @EmbeddedId
    private CurrentMutualFundSerializer currentMutualFundSerializer;

    @Column(name = "nav")
    private Double nav;

    @Column(name = "mutual_fund_value")
    private Double mutualFundValue;

    @CreationTimestamp
    @Column(name = "audit_create_timestamp", nullable = false, updatable = false)
    private Instant auditCreateTimestamp;

    @UpdateTimestamp
    @Column(name = "audit_update_timestamp")
    private Instant auditUpdateTimestamp;
}
