package com.github.avivijay19.lifeledger.commons.dto.mf.sip.request;

import com.github.avivijay19.mutualFund.InvestmentSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * @author : avinashvijayvargiya
 * @created : 16/03/25, Sunday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SipRequest {
    private String schemeCode;

    private InvestmentSource investmentSource;

    private LocalDate firstInvestmentDate;

    private double amountInvested;

    private String cornJob;
}
