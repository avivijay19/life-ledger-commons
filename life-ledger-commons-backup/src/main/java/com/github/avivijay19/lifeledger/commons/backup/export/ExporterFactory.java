package com.github.avivijay19.lifeledger.commons.backup.export;

public final class ExporterFactory {

    private ExporterFactory() {
    }

    public static Exporter create(String format) {
        return switch (format) {
            case "parquet" -> new ParquetExporter();
            default -> throw new IllegalArgumentException("Unknown export format: " + format);
        };
    }
}
