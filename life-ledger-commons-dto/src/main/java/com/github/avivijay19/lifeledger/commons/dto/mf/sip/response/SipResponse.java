package com.github.avivijay19.lifeledger.commons.dto.mf.sip.response;

import com.github.avivijay19.mutualFund.InvestmentSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * @author : avinashvijayvargiya
 * @created : 17/03/25, Monday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SipResponse {
    private String schemeCode;

    private InvestmentSource investmentSource;

    private LocalDate firstInvestmentDate;

    private LocalDate nextDateOfInvestment;

    private double investedAmount;

    private String cronJob;
}
