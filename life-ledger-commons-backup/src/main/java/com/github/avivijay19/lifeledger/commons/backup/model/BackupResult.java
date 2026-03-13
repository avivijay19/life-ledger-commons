package com.github.avivijay19.lifeledger.commons.backup.model;

import java.time.Instant;

public record BackupResult(
    String filePath,
    int rowCount,
    long fileSizeBytes,
    String tableName,
    String domain,
    Instant timestamp
) {
}
