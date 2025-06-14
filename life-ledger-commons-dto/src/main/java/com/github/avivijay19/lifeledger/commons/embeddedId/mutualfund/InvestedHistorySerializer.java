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
 * @created : 08/03/25, Saturday
 **/

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class InvestedHistorySerializer implements Serializable {

    private LocalDate dateInvested;

    private String schemeCode;

    private String folioNumber;
}
