package com.github.avivijay19.lifeledger.commons.backup.partition;

import java.nio.file.Path;
import java.time.Instant;

public interface PartitionStrategy {

    Path resolve(String domain, String tableName, Instant backupTime);
}
