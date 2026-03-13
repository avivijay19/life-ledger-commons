package com.github.avivijay19.lifeledger.commons.backup.status;

import java.time.Instant;

public record BackupStatusEvent(
    BackupStatus status,
    String tableName,
    int rowCount,
    String filePath,
    long fileSizeBytes,
    String errorMessage,
    String triggeredBy,
    Instant timestamp
) {

    public static BackupStatusEvent inProgress(String tableName, String triggeredBy) {
        return new BackupStatusEvent(
            BackupStatus.IN_PROGRESS, tableName, 0, null, 0, null, triggeredBy, Instant.now());
    }

    public static BackupStatusEvent success(String tableName, int rowCount, String filePath,
                                            long fileSizeBytes, String triggeredBy) {
        return new BackupStatusEvent(
            BackupStatus.SUCCESS, tableName, rowCount, filePath, fileSizeBytes, null,
            triggeredBy, Instant.now());
    }

    public static BackupStatusEvent failed(String tableName, String errorMessage,
                                           String triggeredBy) {
        return new BackupStatusEvent(
            BackupStatus.FAILED, tableName, 0, null, 0, errorMessage, triggeredBy, Instant.now());
    }
}
