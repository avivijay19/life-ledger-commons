package com.github.avivijay19.lifeledger.commons.dto.mf.invested.request;

import com.github.avivijay19.lifeledger.commons.embeddedId.mutualfund.InvestedSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : avinashvijayvargiya
 * @created : 16/03/25, Sunday
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InvestedMutualFundRequestMap {
    private InvestedSerializer key;

    private InvestedMutualFund value;
}
