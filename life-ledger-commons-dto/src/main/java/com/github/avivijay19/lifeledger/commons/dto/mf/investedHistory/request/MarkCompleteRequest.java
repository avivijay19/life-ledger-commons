package com.github.avivijay19.lifeledger.commons.dto.mf.investedHistory.request;

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
public class MarkCompleteRequest {
    private LocalDate dateInvested;

    private String schemeCode;
}
