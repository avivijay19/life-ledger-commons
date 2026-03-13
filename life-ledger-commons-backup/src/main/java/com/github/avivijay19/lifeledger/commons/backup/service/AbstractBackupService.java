package com.github.avivijay19.lifeledger.commons.backup.service;

import com.github.avivijay19.lifeledger.commons.backup.config.BackupConfig;
import com.github.avivijay19.lifeledger.commons.backup.export.ExporterFactory;
import com.github.avivijay19.lifeledger.commons.backup.model.BackupResult;
import com.github.avivijay19.lifeledger.commons.backup.sheets.SheetEntityMapper;
import com.github.avivijay19.lifeledger.commons.backup.status.BackupStatusEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public abstract class AbstractBackupService {

    private static final Logger log = LoggerFactory.getLogger(AbstractBackupService.class);

    public final BackupResult backup(BackupConfig config) {
        notifyListeners(config, BackupStatusEvent.inProgress(
            config.getTableName(), config.getTriggeredBy()));

        try {
            validate(config);
            var data = readSource(config);
            if (data.isEmpty()) {
                log.warn("No data found for table: {}", config.getTableName());
            }
            var headers = resolveHeaders(config, data);
            var filePath = resolvePartition(config);
            var absolutePath = config.getOutputDir().resolve(filePath);

            var exporter = ExporterFactory.create("parquet");
            exporter.export(data, headers, absolutePath);

            var fileSize = Files.size(absolutePath);
            var result = new BackupResult(
                filePath.toString(), data.size(), fileSize,
                config.getTableName(), config.getDomain(), Instant.now());

            notifyListeners(config, BackupStatusEvent.success(
                config.getTableName(), data.size(), filePath.toString(),
                fileSize, config.getTriggeredBy()));

            log.info("Backup completed: {} rows → {}", data.size(), absolutePath);
            return result;
        } catch (Exception e) {
            notifyListeners(config, BackupStatusEvent.failed(
                config.getTableName(), e.getMessage(), config.getTriggeredBy()));
            throw new RuntimeException("Backup failed for table: " + config.getTableName(), e);
        }
    }

    protected abstract List<Map<String, String>> readSource(BackupConfig config);

    protected void validate(BackupConfig config) {
        if (config.getTableName() == null || config.getTableName().isBlank()) {
            throw new IllegalArgumentException("Table name is required");
        }
        if (config.getOutputDir() == null) {
            throw new IllegalArgumentException("Output directory is required");
        }
    }

    private List<String> resolveHeaders(BackupConfig config, List<Map<String, String>> data) {
        if (config.getEntityClass() != null) {
            return SheetEntityMapper.getColumnNames(config.getEntityClass());
        }
        if (!data.isEmpty()) {
            return List.copyOf(data.getFirst().keySet());
        }
        return List.of();
    }

    private Path resolvePartition(BackupConfig config) {
        return config.getPartitionStrategy().resolve(
            config.getDomain(), config.getTableName(), Instant.now());
    }

    private void notifyListeners(BackupConfig config, BackupStatusEvent event) {
        for (var listener : config.getListeners()) {
            try {
                listener.onStatusChange(event);
            } catch (Exception e) {
                log.error("Listener failed for event {}: {}", event.status(), e.getMessage());
            }
        }
    }
}
