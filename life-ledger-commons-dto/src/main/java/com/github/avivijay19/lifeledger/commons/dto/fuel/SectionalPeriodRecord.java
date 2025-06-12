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
public class SectionalPeriodRecord {

    private Double fuelCost;

    private Double mileage;

    private Double litre;

    private Double distanceCovered;
}
