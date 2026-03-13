package com.github.avivijay19.lifeledger.commons.embeddedId.mutualfund;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author : avinashvijayvargiya
 * @created : 11/03/25, Tuesday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Embeddable
public class InvestedSerializer implements Serializable {

    private String id;

    private String schemeCode;

    private String folioNumber;

}
