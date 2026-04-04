package com.github.avivijay19.lifeledger.commons.dto.stock.request;

import com.github.avivijay19.lifeledger.commons.enumeration.stock.Broker;
import com.github.avivijay19.lifeledger.commons.enumeration.stock.Currency;
import com.github.avivijay19.lifeledger.commons.enumeration.stock.Exchange;
import com.github.avivijay19.lifeledger.commons.enumeration.stock.HoldingSource;
import com.github.avivijay19.lifeledger.commons.enumeration.stock.Sector;

public record HoldingCreateRequest(
    String symbol,
    Exchange exchange,
    double quantity,
    double avgPrice,
    Broker broker,
    Currency currency,
    Sector sector,
    HoldingSource holdingSource
) {
}
