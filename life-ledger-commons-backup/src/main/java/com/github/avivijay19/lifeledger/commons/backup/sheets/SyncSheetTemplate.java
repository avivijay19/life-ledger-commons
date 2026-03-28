package com.github.avivijay19.lifeledger.commons.backup.sheets;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncSheetTemplate {

    private static final Logger log = LoggerFactory.getLogger(SyncSheetTemplate.class);

    private final GoogleSheetClient client;

    public SyncSheetTemplate(GoogleSheetClient client) {
        this.client = client;
    }

    public <T> void syncToSheet(String sheetTitle, List<T> entities,
                                 Class<T> entityClass, List<String> primaryKeys) {
        try {
            client.clearSheet(sheetTitle);
            var headers = SheetEntityMapper.getColumnHeaders(entityClass);
            var tableData = SheetEntityMapper.getTableData(entities);
            List<List<Object>> rows = new ArrayList<>();
            rows.add(headers);

            Map<String, Integer> columnIndexMap = new HashMap<>();
            for (int i = 0; i < headers.size(); i++) {
                columnIndexMap.put(headers.get(i).toString(), i);
            }

            for (var row : tableData) {
                List<Object> rowList = new ArrayList<>(
                    Collections.nCopies(headers.size(), ""));
                for (var entry : row.entrySet()) {
                    if (columnIndexMap.containsKey(entry.getKey())) {
                        rowList.set(columnIndexMap.get(entry.getKey()), entry.getValue());
                    }
                }
                rows.add(rowList);
            }

            client.writeSheet(rows, sheetTitle);
            log.info("Synced {} rows to sheet '{}'.", entities.size(), sheetTitle);
        } catch (IOException e) {
            throw new UncheckedIOException(
                "Failed to sync to sheet '%s'".formatted(sheetTitle), e);
        }
    }

    public <T> List<T> readEntitiesFromSheet(String sheetTitle, Class<T> entityClass) {
        try {
            var rows = client.readSheet(sheetTitle);
            if (rows == null || rows.size() <= 1) {
                return List.of();
            }
            var maps = SheetEntityMapper.rowsToMaps(rows);
            return maps.stream()
                .map(row -> SheetEntityMapper.mapToEntity(row, entityClass))
                .toList();
        } catch (IOException e) {
            throw new UncheckedIOException(
                "Failed to read from sheet '%s'".formatted(sheetTitle), e);
        }
    }
}
