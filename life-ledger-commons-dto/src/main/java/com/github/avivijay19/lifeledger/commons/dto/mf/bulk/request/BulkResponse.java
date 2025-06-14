package com.github.avivijay19.lifeledger.commons.dto.mf.bulk.request;

import com.github.avivijay19.lifeledger.commons.enumeration.mutualFund.InvestmentSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * @author : avinashvijayvargiya
 * @created : 14/03/25, Friday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BulkResponse {

    private LocalDate dateInvested;

    private Double totalUnits;

    private LocalDate dateCompleted;

    private String folioNumber;

    private String schemeCode;

    private double totalAmountInvested;

    private InvestmentSource investmentSource;
}
