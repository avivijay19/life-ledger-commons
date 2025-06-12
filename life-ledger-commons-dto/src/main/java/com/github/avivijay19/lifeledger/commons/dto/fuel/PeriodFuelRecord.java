package com.github.avivijay19.lifeledger.commons.dto.fuel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : avinashvijayvargiya
 * @created : 18/12/24, Wednesday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PeriodFuelRecord {

    private int count;

    private Double fuelFilled;

    private Double fuelCostTotal;

    private Double mileageTotal;

    private Double litreTotal;

    private Double distanceTotalCovered;
}
