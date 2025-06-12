package com.github.avivijay19.lifeledger.commons.embeddedId;

import com.github.avivijay19.fuel.VehicleNumber;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author : avinashvijayvargiya
 * @created : 01/01/25, Wednesday
 **/

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FuelSerializer implements Serializable {
    private long time;

    private VehicleNumber vehicleNumber;

    private long odoMetreReading;
}
