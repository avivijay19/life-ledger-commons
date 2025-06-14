package com.github.avivijay19.lifeledger.commons.dto.mf.mfapi;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author : avinashvijayvargiya
 * @created : 09/03/25, Sunday
 **/

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
@Builder
public class MFApi {

    @SerializedName("meta")
    Meta meta;

    @SerializedName("data")
    List<Data> data;

    @SerializedName("status")
    String status;
}
