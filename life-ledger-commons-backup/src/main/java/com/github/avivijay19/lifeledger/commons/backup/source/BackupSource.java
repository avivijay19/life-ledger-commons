package com.github.avivijay19.lifeledger.commons.backup.source;

import com.github.avivijay19.lifeledger.commons.backup.config.BackupConfig;
import java.util.List;
import java.util.Map;

public interface BackupSource {

    List<Map<String, String>> readData(BackupConfig config);
}
