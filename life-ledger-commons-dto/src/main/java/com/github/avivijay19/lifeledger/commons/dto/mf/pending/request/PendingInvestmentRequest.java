package com.github.avivijay19.lifeledger.commons.dto.mf.pending.request;

import com.github.avivijay19.mutualFund.InvestmentSource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * @author : avinashvijayvargiya
 * @created : 08/03/25, Saturday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PendingInvestmentRequest {

    private String schemeCode;

    private LocalDate investmentDate;

    private double investedAmount;

    private InvestmentSource investmentSource;
}
