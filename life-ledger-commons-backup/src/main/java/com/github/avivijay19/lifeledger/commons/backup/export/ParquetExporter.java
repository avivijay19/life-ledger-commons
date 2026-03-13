package com.github.avivijay19.lifeledger.commons.backup.export;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetFileWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

public class ParquetExporter implements Exporter {

    private static final Logger log = LoggerFactory.getLogger(ParquetExporter.class);

    @Override
    public void export(List<Map<String, String>> data, List<String> headers,
                       java.nio.file.Path filePath) throws IOException {
        if (data.isEmpty()) {
            throw new IllegalArgumentException("No data to export");
        }

        Files.createDirectories(filePath.getParent());

        var schema = buildSchema(headers);
        var hadoopPath = new Path(filePath.toAbsolutePath().toString());
        var conf = new Configuration();

        try (var writer = AvroParquetWriter.<GenericRecord>builder(hadoopPath)
            .withSchema(schema)
            .withCompressionCodec(CompressionCodecName.SNAPPY)
            .withWriteMode(ParquetFileWriter.Mode.OVERWRITE)
            .withConf(conf)
            .build()) {

            for (var row : data) {
                var record = new GenericData.Record(schema);
                for (var header : headers) {
                    record.put(sanitizeFieldName(header), row.getOrDefault(header, ""));
                }
                writer.write(record);
            }
        }

        log.info("Exported {} rows to {}", data.size(), filePath);
    }

    private Schema buildSchema(List<String> headers) {
        var builder = SchemaBuilder.record("BackupRecord")
            .namespace("com.github.avivijay19.lifeledger.backup")
            .fields();
        for (var header : headers) {
            builder = builder.name(sanitizeFieldName(header))
                .type().nullable().stringType().noDefault();
        }
        return builder.endRecord();
    }

    static String sanitizeFieldName(String name) {
        return name.replaceAll("[^a-zA-Z0-9_]", "_");
    }
}
