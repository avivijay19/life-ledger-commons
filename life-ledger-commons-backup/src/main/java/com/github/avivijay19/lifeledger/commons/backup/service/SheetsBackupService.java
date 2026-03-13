package com.github.avivijay19.lifeledger.commons.backup.service;

import com.github.avivijay19.lifeledger.commons.backup.config.BackupConfig;
import com.github.avivijay19.lifeledger.commons.backup.source.BackupSource;
import java.util.List;
import java.util.Map;

public class SheetsBackupService extends AbstractBackupService {

    private final BackupSource source;

    public SheetsBackupService(BackupSource source) {
        this.source = source;
    }

    @Override
    protected List<Map<String, String>> readSource(BackupConfig config) {
        return source.readData(config);
    }
}
