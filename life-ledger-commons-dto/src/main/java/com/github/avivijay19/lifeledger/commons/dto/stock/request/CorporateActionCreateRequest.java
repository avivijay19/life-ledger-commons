package com.github.avivijay19.lifeledger.commons.dto.stock.request;

import com.github.avivijay19.lifeledger.commons.enumeration.stock.CorporateActionType;
import java.time.LocalDate;

public record CorporateActionCreateRequest(
    String symbol,
    CorporateActionType actionType,
    LocalDate recordDate,
    String ratio,
    String newSymbol
) {
}
