package com.github.avivijay19.lifeledger.commons.dto.stock.request;

import com.github.avivijay19.lifeledger.commons.enumeration.stock.Broker;
import com.github.avivijay19.lifeledger.commons.enumeration.stock.Exchange;
import com.github.avivijay19.lifeledger.commons.enumeration.stock.StockTransactionType;
import java.time.LocalDate;

public record TradeCreateRequest(
    String symbol,
    Exchange exchange,
    StockTransactionType type,
    double quantity,
    double price,
    Broker broker,
    LocalDate tradeDate,
    String orderId
) {
}
