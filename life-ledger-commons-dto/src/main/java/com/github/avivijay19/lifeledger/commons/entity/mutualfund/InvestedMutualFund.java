package com.github.avivijay19.lifeledger.commons.entity.mutualfund;

import com.github.avivijay19.lifeledger.commons.embeddedId.mutualfund.InvestedSerializer;
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

/**
 * @author : avinashvijayvargiya
 * @created : 20/02/25, Thursday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "invested_mutual_fund")
public class InvestedMutualFund {

    @EmbeddedId
    private InvestedSerializer investedSerializer;

    @Column(name = "total_amount_invested")
    private double totalAmountInvested;

    @Column(name = "total_invested_units")
    private double totalInvestedUnits;

    @Column(name = "investment_source", nullable = false)
    private InvestmentSource investmentSource;

    @Column(name = "fund_house", nullable = false)
    private String fundHouse;

    @Column(name = "scheme_type", nullable = false)
    private String schemeType;

    @Column(name = "scheme_category")
    private String schemeCategory;

    @Column(name = "scheme_name", nullable = false)
    private String schemeName;

    @Column(name = "isin_growth")
    private String isinGrowth;

    @Column(name = "isin_div_reinvestment")
    private String isinDivReinvestment;

    @CreationTimestamp
    @Column(name = "audit_created_timestamp", nullable = false, updatable = false)
    private Instant auditCreateTimestamp;

    @UpdateTimestamp
    @Column(name = "audit_update_timestamp")
    private Instant auditUpdateTimestamp;
}
