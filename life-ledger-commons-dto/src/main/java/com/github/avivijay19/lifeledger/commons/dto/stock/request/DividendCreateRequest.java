package com.github.avivijay19.lifeledger.commons.dto.stock.request;

import java.time.LocalDate;

public record DividendCreateRequest(
    String symbol,
    double amount,
    LocalDate recordDate,
    LocalDate exDate,
    double tdsAmount
) {
}
