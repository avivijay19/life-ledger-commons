package com.github.avivijay19.lifeledger.commons.dto.mf.current.request;

import com.github.avivijay19.lifeledger.commons.dto.mf.bulk.request.BulkCurrentMutualFund;
import com.github.avivijay19.lifeledger.commons.embeddedId.mutualfund.CurrentMutualFundSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : avinashvijayvargiya
 * @created : 15/03/25, Saturday
 **/

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class CurrentMutualFundRequestMap {
    private CurrentMutualFundSerializer key;

    private BulkCurrentMutualFund value;
}
