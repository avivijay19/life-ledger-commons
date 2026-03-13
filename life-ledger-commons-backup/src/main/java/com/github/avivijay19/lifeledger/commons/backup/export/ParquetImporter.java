package com.github.avivijay19.lifeledger.commons.backup.export;

import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ParquetImporter {

    private static final Logger log = LoggerFactory.getLogger(ParquetImporter.class);

    public List<Map<String, String>> read(java.nio.file.Path filePath) throws IOException {
        var hadoopPath = new Path(filePath.toAbsolutePath().toString());
        var conf = new Configuration();
        var rows = new ArrayList<Map<String, String>>();

        try (var reader = AvroParquetReader.<GenericRecord>builder(hadoopPath)
            .withConf(conf)
            .build()) {

            GenericRecord record;
            while ((record = reader.read()) != null) {
                var row = new LinkedHashMap<String, String>();
                for (var field : record.getSchema().getFields()) {
                    var value = record.get(field.name());
                    row.put(field.name(), value != null ? value.toString() : null);
                }
                rows.add(row);
            }
        }

        log.info("Read {} rows from {}", rows.size(), filePath);
        return rows;
    }

    public int getRowCount(java.nio.file.Path filePath) throws IOException {
        return read(filePath).size();
    }
}
