package com.github.avivijay19.lifeledger.commons.embeddedId.mutualfund;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author : avinashvijayvargiya
 * @created : 12/03/25, Wednesday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class CurrentMutualFundSerializer implements Serializable {

    private String folioNumber;

    private LocalDate asOfDate;

    private String schemeCode;

}
