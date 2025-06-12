package com.github.avivijay19.lifeledger.commons.embeddedId.mutualfund;

import com.github.avivijay19.mutualFund.InvestmentSource;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author : avinashvijayvargiya
 * @created : 02/03/25, Sunday
 **/

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SipSerializer implements Serializable {
    private String schemeCode;

    private InvestmentSource investmentSource;

    private LocalDate sipCreatedOn;
}
