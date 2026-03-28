package com.github.avivijay19.lifeledger.commons.backup.sheets;

import java.util.List;

public record SheetSyncConfig<T>(
    String sheetTitle,
    Class<T> entityClass,
    List<String> primaryKeys
) {
}
