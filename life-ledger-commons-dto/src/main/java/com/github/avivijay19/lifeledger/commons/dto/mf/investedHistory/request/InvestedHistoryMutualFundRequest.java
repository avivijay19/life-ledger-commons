package com.github.avivijay19.lifeledger.commons.dto.mf.investedHistory.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * @author : avinashvijayvargiya
 * @created : 03/03/25, Monday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvestedHistoryMutualFundRequest {

    private String schemeCode;

    private double amountInvested;

    private UUID uuid;
}
