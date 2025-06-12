package com.github.avivijay19.lifeledger.commons.entity.mutualfund;

import com.github.avivijay19.mutualFund.InvestmentSource;
import com.github.avivijay19.mutualFund.InvestmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import java.time.Instant;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pending_investment")
public class PendingInvestment {

    @Id
    @UuidGenerator
    private String id;

    @Column(name = "schema_code")
    private String schemeCode;

    @Column(name = "investment_date")
    private LocalDate investmentDate;

    @Column(name = "invested_amount")
    private double investedAmount;

    @Column(name = "investment_source")
    private InvestmentSource investmentSource;

    @Column(name = "investment_status")
    private InvestmentStatus investmentStatus;

    @CreationTimestamp
    @Column(name = "audit_create_timestamp", updatable = false, nullable = false)
    private Instant auditCreateTimestamp;

    @UpdateTimestamp
    @Column(name = "audit_update_timestamp")
    private Instant auditUpdateTimestamp;

    public PendingInvestment(String schemeCode, LocalDate investmentDate, double investedAmount,
                             InvestmentSource investmentSource, InvestmentStatus investmentStatus) {
        this.schemeCode = schemeCode;
        this.investmentDate = investmentDate;
        this.investedAmount = investedAmount;
        this.investmentSource = investmentSource;
        this.investmentStatus = investmentStatus;
    }

    public PendingInvestment(String schemeCode, LocalDate investmentDate, double investedAmount,
                             InvestmentSource investmentSource, InvestmentStatus investmentStatus,
                             Instant auditCreateTimestamp, Instant auditUpdateTimestamp) {
        this.schemeCode = schemeCode;
        this.investmentDate = investmentDate;
        this.investedAmount = investedAmount;
        this.investmentSource = investmentSource;
        this.investmentStatus = investmentStatus;
        this.auditCreateTimestamp = auditCreateTimestamp;
        this.auditUpdateTimestamp = auditUpdateTimestamp;
    }
}
