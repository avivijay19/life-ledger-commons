package com.github.avivijay19.lifeledger.commons.entity.mutualfund;

import com.github.avivijay19.lifeledger.commons.embeddedId.mutualfund.InvestedHistorySerializer;
import com.github.avivijay19.lifeledger.commons.enumeration.mutualFund.InvestmentSource;
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
import java.time.LocalDate;

/**
 * @author : avinashvijayvargiya
 * @created : 03/03/25, Monday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "invested_history_mutual_fund")
public class InvestedHistoryMutualFund {

    @EmbeddedId
    private InvestedHistorySerializer investedHistorySerializer;

    @Column(name = "amount_invested")
    private double amountInvested;

    @Column(name = "invested_units")
    private Double investedUnits;

    @Column(name = "investment_source", nullable = false)
    private InvestmentSource investmentSource;

    @Column(name = "investment_completed", nullable = false)
    private LocalDate investmentCompleted;

    @Column(name = "to_be_calculated")
    private boolean toBeCalculated;

    @CreationTimestamp
    @Column(name = "audit_create_timestamp", updatable = false, nullable = false)
    private Instant auditCreateTimestamp;

    @UpdateTimestamp
    @Column(name = "audit_update_timestamp")
    private Instant auditUpdateTimestamp;
}
