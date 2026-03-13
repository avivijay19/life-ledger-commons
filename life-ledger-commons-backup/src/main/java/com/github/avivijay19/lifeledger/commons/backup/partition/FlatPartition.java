package com.github.avivijay19.lifeledger.commons.backup.partition;

import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class FlatPartition implements PartitionStrategy {

    private static final DateTimeFormatter FILE_TS =
        DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    @Override
    public Path resolve(String domain, String tableName, Instant backupTime) {
        var zdt = ZonedDateTime.ofInstant(backupTime, ZoneId.of("Asia/Kolkata"));
        var fileName = tableName.toLowerCase().replace("_", "-")
            + "-" + zdt.format(FILE_TS) + ".parquet";
        return Path.of(domain, tableName.toLowerCase().replace("_", "-"), fileName);
    }
}
