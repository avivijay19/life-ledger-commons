package com.github.avivijay19.lifeledger.commons.entity.mutualfund;

import com.github.avivijay19.lifeledger.commons.entity.AuditableEntity;
import com.github.avivijay19.lifeledger.commons.enumeration.mutualFund.InvestmentSource;
import com.github.avivijay19.lifeledger.commons.enumeration.mutualFund.SipStatus;
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
 * @created : 20/02/25, Thursday
 **/

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sip_mutual_fund", schema = "mutual_fund")
public class SipMutualFund extends AuditableEntity {

    @Id
    @UuidGenerator
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "scheme_code", nullable = false)
    private String schemeCode;

    @Column(name = "investment_source", nullable = false)
    private InvestmentSource investmentSource;

    @Column(name = "sip_created_on", nullable = false)
    private LocalDate sipCreatedOn;

    @Column(name = "amount_invested")
    private double amountInvested;

    @Column(name = "cron_job")
    private String cronJob;

    @Column(name = "next_date")
    private LocalDate nextDate;

    @Column(name = "count_of_investment")
    private int countOfInvestment;

    @Column(name = "sip_start_date")
    private LocalDate sipStartDate;

    @Column(name = "last_execution_date")
    private LocalDate lastExecutionDate;

    @Column(name = "sip_status")
    private SipStatus sipStatus;       // ACTIVE, PAUSED, CANCELLED
}