package com.github.avivijay19.lifeledger.commons.dto.mf.mfapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * @author : avinashvijayvargiya
 * @created : 09/03/25, Sunday
 **/

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class Data {
    @JsonProperty("date")
    String date;

    @JsonProperty("nav")
    String nav;
}
