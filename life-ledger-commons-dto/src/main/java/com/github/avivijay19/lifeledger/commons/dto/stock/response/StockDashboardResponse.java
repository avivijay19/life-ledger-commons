package com.github.avivijay19.lifeledger.commons.dto.stock.response;

public record StockDashboardResponse(
    double totalInvested,
    double currentValue,
    double totalPnl,
    double totalPnlPct,
    double dayChange,
    double dayChangePct,
    int holdingsCount,
    String topGainer,
    String topLoser
) {
}
