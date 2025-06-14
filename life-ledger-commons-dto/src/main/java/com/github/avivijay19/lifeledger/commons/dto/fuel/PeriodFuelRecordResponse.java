package com.github.avivijay19.lifeledger.commons.dto.fuel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author : avinashvijayvargiya
 * @created : 18/12/24, Wednesday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PeriodFuelRecordResponse {
    private PeriodFuelRecord periodFuelRecord;

    private List<SectionalPeriodRecord> sectionalPeriodRecord;
}
