package com.github.avivijay19.lifeledger.commons.dto.stock.response;

public record TaxReportResponse(
    double stcgTotal,
    double ltcgTotal,
    double stcgTax,
    double ltcgTax,
    double ltcgExemption,
    double dividendIncome,
    double tdsTotal
) {
}
