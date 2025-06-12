package com.github.avivijay19.lifeledger.commons.dto.mf.invested.request;

import com.github.avivijay19.mutualFund.InvestmentSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * @author : avinashvijayvargiya
 * @created : 20/02/25, Thursday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvestedMutualFund {

    private double totalAmountInvested;

    private double totalInvestedUnits;

    private InvestmentSource investmentSource;

    private String fundHouse;

    private String schemeType;

    private String schemeCategory;

    private String schemeName;

    private String isinGrowth;

    private String isinDivReinvestment;

    private LocalDate dateOfInvestmentCompleted;
}
