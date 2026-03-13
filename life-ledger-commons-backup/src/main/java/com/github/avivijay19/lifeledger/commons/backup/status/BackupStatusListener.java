package com.github.avivijay19.lifeledger.commons.backup.status;

public interface BackupStatusListener {

    void onStatusChange(BackupStatusEvent event);
}
