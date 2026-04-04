package com.github.avivijay19.lifeledger.commons.dto.stock.request;

import com.github.avivijay19.lifeledger.commons.enumeration.stock.Broker;

public record BrokerAccountRequest(
    Broker broker,
    String clientId,
    String apiKey
) {
}
