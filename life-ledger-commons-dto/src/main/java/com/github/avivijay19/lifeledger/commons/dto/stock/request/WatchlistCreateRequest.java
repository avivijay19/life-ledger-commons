package com.github.avivijay19.lifeledger.commons.dto.stock.request;

import java.util.List;

public record WatchlistCreateRequest(
    String name,
    List<String> symbols
) {
}
