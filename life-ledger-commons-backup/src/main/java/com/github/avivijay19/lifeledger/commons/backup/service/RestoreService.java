package com.github.avivijay19.lifeledger.commons.backup.service;

import com.github.avivijay19.lifeledger.commons.backup.export.ParquetImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class RestoreService {

    private static final Logger log = LoggerFactory.getLogger(RestoreService.class);

    private final ParquetImporter importer;

    public RestoreService() {
        this.importer = new ParquetImporter();
    }

    public <T> int restore(Path parquetFile, Function<Map<String, String>, T> mapper,
                           Consumer<List<T>> batchSaver) throws IOException {
        var rows = importer.read(parquetFile);
        if (rows.isEmpty()) {
            log.warn("No rows found in {}", parquetFile);
            return 0;
        }

        var entities = rows.stream().map(mapper).toList();
        batchSaver.accept(entities);

        log.info("Restored {} entities from {}", entities.size(), parquetFile);
        return entities.size();
    }
}
