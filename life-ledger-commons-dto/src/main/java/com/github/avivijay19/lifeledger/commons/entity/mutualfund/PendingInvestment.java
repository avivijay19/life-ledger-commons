package com.github.avivijay19.lifeledger.commons.entity.mutualfund;

import com.github.avivijay19.lifeledger.commons.entity.AuditableEntity;
import com.github.avivijay19.lifeledger.commons.enumeration.mutualFund.InvestmentSource;
import com.github.avivijay19.lifeledger.commons.enumeration.mutualFund.InvestmentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "pending_investment", schema = "mutual_fund")
public class PendingInvestment extends AuditableEntity {

    @Id
    @UuidGenerator
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "schema_code")
    private String schemeCode;

    @Column(name = "investment_date")
    private LocalDate investmentDate;

    @Column(name = "invested_amount")
    private double investedAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "investment_source")
    private InvestmentSource investmentSource;

    @Enumerated(EnumType.STRING)
    @Column(name = "investment_status")
    private InvestmentStatus investmentStatus;

    @Column(name = "is_nfo_investment")
    private boolean isNfoInvestment;

    @Column(name = "nfo_default_nav_value")
    private double nfoDefaultNavValue;
}
