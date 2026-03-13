package com.github.avivijay19.lifeledger.commons.backup.service;

import com.github.avivijay19.lifeledger.commons.backup.export.ParquetExporter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class RestoreServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void restore_mapsAndSavesEntities() throws IOException {
        // Prepare parquet file
        var headers = List.of("id", "name", "value");
        var data = List.<Map<String, String>>of(
            Map.of("id", "1", "name", "test1", "value", "100"),
            Map.of("id", "2", "name", "test2", "value", "200")
        );
        var file = tempDir.resolve("restore-test.parquet");
        new ParquetExporter().export(data, headers, file);

        // Restore
        var saved = new ArrayList<Map<String, String>>();
        var service = new RestoreService();
        var count = service.restore(
            file,
            row -> row,
            saved::addAll
        );

        assertThat(count).isEqualTo(2);
        assertThat(saved).hasSize(2);
        assertThat(saved.get(0).get("name")).isEqualTo("test1");
    }
}
