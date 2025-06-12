package com.github.avivijay19.lifeledger.commons.dto.sheets;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashMap;
import java.util.List;

/**
 * @author : avinashvijayvargiya
 * @created : 01/01/25, Wednesday
 **/

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class SheetUpdateRequest {
    private List<HashMap<String, String>> dataToBeAdded;

    private List<String> primaryKeys;

    private String sheetName;

    public SheetUpdateRequest(List<String> primaryKeys, String sheetName) {
        this.primaryKeys = primaryKeys;
        this.sheetName = sheetName;
    }
}

