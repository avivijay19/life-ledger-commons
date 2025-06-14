package com.github.avivijay19.lifeledger.commons.dto.mf.bulk.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : avinashvijayvargiya
 * @created : 15/03/25, Saturday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BulkCurrentMutualFund {

    private Double nav;

    private Double mutualFundValue;
}
