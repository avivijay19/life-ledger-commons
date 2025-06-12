package com.github.avivijay19.lifeledger.commons.dto.mf.pending.request;

import com.github.avivijay19.lifeledger.commons.enumeration.mutualFund.InvestmentStatus;
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
@Builder
@Data
public class PendingInvestmentMarkStatusRequest {
    private String uuid;

    private LocalDate investmentCompletedDate;

    private InvestmentStatus investmentStatus;

    private String folioNumber;
}
