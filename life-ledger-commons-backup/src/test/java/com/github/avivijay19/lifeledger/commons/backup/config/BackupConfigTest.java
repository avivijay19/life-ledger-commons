package com.github.avivijay19.lifeledger.commons.backup.config;

import com.github.avivijay19.lifeledger.commons.backup.partition.YearMonthPartition;
import com.github.avivijay19.lifeledger.commons.backup.status.BackupStatusListener;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BackupConfigTest {

    @Test
    void builder_createsConfig() {
        var config = BackupConfig.builder()
            .partitionStrategy(new YearMonthPartition())
            .outputDir(Path.of("/tmp/backups"))
            .tableName("SIP_MUTUAL_FUND")
            .domain("mutual-fund")
            .triggeredBy("airflow")
            .build();

        assertThat(config.getTableName()).isEqualTo("SIP_MUTUAL_FUND");
        assertThat(config.getDomain()).isEqualTo("mutual-fund");
        assertThat(config.getTriggeredBy()).isEqualTo("airflow");
        assertThat(config.getListeners()).isEmpty();
    }

    @Test
    void builder_defaultTriggeredBy() {
        var config = BackupConfig.builder()
            .partitionStrategy(new YearMonthPartition())
            .outputDir(Path.of("/tmp"))
            .tableName("TEST")
            .domain("test")
            .build();

        assertThat(config.getTriggeredBy()).isEqualTo("system");
    }

    @Test
    void builder_withListeners() {
        BackupStatusListener listener = event -> {};
        var config = BackupConfig.builder()
            .partitionStrategy(new YearMonthPartition())
            .outputDir(Path.of("/tmp"))
            .tableName("TEST")
            .domain("test")
            .addListener(listener)
            .build();

        assertThat(config.getListeners()).hasSize(1);
    }

    @Test
    void builder_missingRequired_throws() {
        assertThatThrownBy(() -> BackupConfig.builder()
            .outputDir(Path.of("/tmp"))
            .tableName("TEST")
            .domain("test")
            .build())
            .isInstanceOf(NullPointerException.class);
    }
}
