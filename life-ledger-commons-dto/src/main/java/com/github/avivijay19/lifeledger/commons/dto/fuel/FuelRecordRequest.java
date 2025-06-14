package com.github.avivijay19.lifeledger.commons.dto.fuel;

import com.github.avivijay19.fuel.FuelBrand;
import com.github.avivijay19.fuel.VehicleNumber;
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
public class FuelRecordRequest {

    private VehicleNumber vehicleNumber;

    private FuelBrand fuelBrand;

    //    Amount paid at fuel station
    private Double fuelCost;

    //    Rate of the Petrol at fuel station
    private Double fuelRate;

    //    odoMetre reading
    private long odoMetreReading;

    //    Trip A reading
    private Double tripAReading;

    //    Location of fuel filled
    private String location;
}
