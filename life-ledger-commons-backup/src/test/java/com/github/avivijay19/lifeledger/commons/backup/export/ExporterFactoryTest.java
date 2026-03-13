package com.github.avivijay19.lifeledger.commons.backup.export;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExporterFactoryTest {

    @Test
    void create_parquet_returnsParquetExporter() {
        var exporter = ExporterFactory.create("parquet");
        assertThat(exporter).isInstanceOf(ParquetExporter.class);
    }

    @Test
    void create_unknownFormat_throws() {
        assertThatThrownBy(() -> ExporterFactory.create("xml"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Unknown export format: xml");
    }
}
