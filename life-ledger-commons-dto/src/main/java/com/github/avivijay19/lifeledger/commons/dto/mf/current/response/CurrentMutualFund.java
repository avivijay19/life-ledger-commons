package com.github.avivijay19.lifeledger.commons.dto.mf.current.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * @author : avinashvijayvargiya
 * @created : 12/03/25, Wednesday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CurrentMutualFund {

    private String id;

    private LocalDate asOfDate;

    private String schemeCode;

    private double nav;
}
