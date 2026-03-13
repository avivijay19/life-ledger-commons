package com.github.avivijay19.lifeledger.commons.backup.export;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface Exporter {

    void export(List<Map<String, String>> data, List<String> headers, Path filePath)
        throws IOException;
}
