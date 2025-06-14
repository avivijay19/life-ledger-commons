package com.github.avivijay19.lifeledger.commons.entity.mutualfund;

import com.github.avivijay19.lifeledger.commons.embeddedId.mutualfund.SipSerializer;
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
 * @created : 20/02/25, Thursday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sip_mutual_fund")
public class SipMutualFund {

    @EmbeddedId
    private SipSerializer sipSerializer;

    @Column(name = "amount_invested")
    private double amountInvested;

    @Column(name = "cron_job")
    private String cronJob;

    @Column(name = "next_date")
    private LocalDate nextDate;

    @Column(name = "count_of_investment")
    private int countOfInvestment;

    @CreationTimestamp
    @Column(name = "audit_create_timestamp", updatable = false, nullable = false)
    private Instant auditCreateTimestamp;

    @UpdateTimestamp
    @Column(name = "audit_update_timestamp")
    private Instant auditUpdateTimestamp;
}
