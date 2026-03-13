package com.github.avivijay19.lifeledger.commons.backup.export;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

class ParquetRoundTripTest {

    @TempDir
    Path tempDir;

    @Test
    void exportAndImport_roundTripsData() throws IOException {
        var headers = List.of("uuid", "scheme_code", "sip_amount");
        var row1 = new LinkedHashMap<String, String>();
        row1.put("uuid", "abc-123");
        row1.put("scheme_code", "119551");
        row1.put("sip_amount", "1000.0");

        var row2 = new LinkedHashMap<String, String>();
        row2.put("uuid", "def-456");
        row2.put("scheme_code", "120503");
        row2.put("sip_amount", "2500.0");

        var data = List.<Map<String, String>>of(row1, row2);
        var filePath = tempDir.resolve("test-backup.parquet");

        var exporter = new ParquetExporter();
        exporter.export(data, headers, filePath);

        assertThat(filePath).exists();

        var importer = new ParquetImporter();
        var imported = importer.read(filePath);

        assertThat(imported).hasSize(2);
        assertThat(imported.get(0).get("uuid")).isEqualTo("abc-123");
        assertThat(imported.get(0).get("scheme_code")).isEqualTo("119551");
        assertThat(imported.get(0).get("sip_amount")).isEqualTo("1000.0");
        assertThat(imported.get(1).get("uuid")).isEqualTo("def-456");
    }

    @Test
    void exportAndImport_handlesNullValues() throws IOException {
        var headers = List.of("name", "optional_field");
        var row = new LinkedHashMap<String, String>();
        row.put("name", "test");
        row.put("optional_field", "");

        var filePath = tempDir.resolve("null-test.parquet");
        new ParquetExporter().export(List.<Map<String, String>>of(row), headers, filePath);

        var imported = new ParquetImporter().read(filePath);
        assertThat(imported).hasSize(1);
        assertThat(imported.get(0).get("name")).isEqualTo("test");
    }

    @Test
    void getRowCount_returnsCorrectCount() throws IOException {
        var headers = List.of("id");
        var row1 = Map.of("id", "1");
        var row2 = Map.of("id", "2");
        var row3 = Map.of("id", "3");

        var filePath = tempDir.resolve("count-test.parquet");
        new ParquetExporter().export(List.of(row1, row2, row3), headers, filePath);

        assertThat(new ParquetImporter().getRowCount(filePath)).isEqualTo(3);
    }
}
