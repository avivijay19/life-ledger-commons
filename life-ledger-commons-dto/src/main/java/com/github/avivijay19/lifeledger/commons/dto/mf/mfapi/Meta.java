package com.github.avivijay19.lifeledger.commons.dto.mf.mfapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : avinashvijayvargiya
 * @created : 09/03/25, Sunday
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Meta {

    @JsonProperty("scheme_type")
    String schemeType;

    @JsonProperty("scheme_category")
    String schemeCategory;

    @JsonProperty("schemeCode")
    int schemeCode;

    @JsonProperty("scheme_name")
    String schemeName;

    @JsonProperty("isin_growth")
    String isinGrowth;

    @JsonProperty("isin_div_reinvestment")
    String isinDivReinvestment;

    @JsonProperty("fund_house")
    private String fundHouse;
}
