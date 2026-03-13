package com.github.avivijay19.lifeledger.commons.backup.source;

import com.github.avivijay19.lifeledger.commons.backup.config.BackupConfig;
import com.github.avivijay19.lifeledger.commons.backup.sheets.GoogleSheetClient;
import com.github.avivijay19.lifeledger.commons.backup.sheets.SheetEntityMapper;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

public class SheetsBackupSource implements BackupSource {

    private final GoogleSheetClient sheetClient;

    public SheetsBackupSource(GoogleSheetClient sheetClient) {
        this.sheetClient = sheetClient;
    }

    @Override
    public List<Map<String, String>> readData(BackupConfig config) {
        try {
            var rows = sheetClient.readSheet(config.getTableName());
            if (rows == null || rows.size() <= 1) {
                return List.of();
            }
            return SheetEntityMapper.rowsToMaps(rows);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read sheet: " + config.getTableName(), e);
        }
    }
}
