package com.github.avivijay19.lifeledger.commons.entity.mutualfund;

import com.github.avivijay19.lifeledger.commons.entity.AuditableEntity;
import com.github.avivijay19.lifeledger.commons.enumeration.mutualFund.InvestmentSource;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.UuidGenerator;
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
@Table(name = "invested_mutual_fund", schema = "mutual_fund", uniqueConstraints = {
    @UniqueConstraint(name = "uk_invested_mf_business_id", columnNames = "id")
})
public class InvestedMutualFund extends AuditableEntity {

    @Id
    @UuidGenerator
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "id", nullable = false, updatable = false)
    private String id;

    @Column(name = "scheme_code", nullable = false)
    private String schemeCode;

    @Column(name = "folio_number", nullable = false)
    private String folioNumber;

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
}
