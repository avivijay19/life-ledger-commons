package com.github.avivijay19.lifeledger.commons.entity.mutualfund;

import com.github.avivijay19.lifeledger.commons.entity.AuditableEntity;
import com.github.avivijay19.lifeledger.commons.enumeration.mutualFund.InvestmentSource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

/**
 * @author : avinashvijayvargiya
 * @created : 03/03/25, Monday
 **/

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "invested_history_mutual_fund", schema = "mutual_fund")
public class InvestedHistoryMutualFund extends AuditableEntity {

    @Id
    @UuidGenerator
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "date_invested", nullable = false)
    private LocalDate dateInvested;

    @Column(name = "scheme_code", nullable = false)
    private String schemeCode;

    @Column(name = "folio_number", nullable = false)
    private String folioNumber;

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
}
