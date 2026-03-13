package com.github.avivijay19.lifeledger.commons.entity.vehicle;

import com.github.avivijay19.fuel.FuelBrand;
import com.github.avivijay19.lifeledger.commons.embeddedId.FuelSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : avinashvijayvargiya
 * @created : 17/12/24, Tuesday
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "fuel", schema = "vehicle")
@Entity
public class Fuel {

    @EmbeddedId
    private FuelSerializer fuelSerializer;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fuelBrand")
    private FuelBrand fuelBrand;

    //    Amount paid at fuel station
    @Column(name = "fuelCost")
    private Double fuelCost;

    //    Rate of the Petrol at fuel station
    @Column(name = "fuelRate")
    private Double fuelRate;

    //    filled litre
    @Column(name = "litre")
    private Double litre;

    //    Trip A reading
    @Column(name = "tripAReading")
    private Double tripAReading;

    @Column(name = "mileage")
    private Double mileage;

    //    Location of fuel filled
    @Column(name = "location")
    private String location;

}
