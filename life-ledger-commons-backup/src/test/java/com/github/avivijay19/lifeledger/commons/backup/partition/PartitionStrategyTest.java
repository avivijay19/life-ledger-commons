package com.github.avivijay19.lifeledger.commons.backup.partition;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import static org.assertj.core.api.Assertions.assertThat;

class PartitionStrategyTest {

    private static final Instant BACKUP_TIME =
        ZonedDateTime.of(2026, 3, 14, 23, 30, 0, 0, ZoneId.of("Asia/Kolkata")).toInstant();

    @Test
    void yearMonthPartition_resolvesCorrectly() {
        var strategy = new YearMonthPartition();
        var path = strategy.resolve("mutual-fund", "SIP_MUTUAL_FUND", BACKUP_TIME);
        assertThat(path.toString()).contains("mutual-fund/sip-mutual-fund/2026/03/");
        assertThat(path.getFileName().toString()).startsWith("sip-mutual-fund-20260314_233000");
        assertThat(path.getFileName().toString()).endsWith(".parquet");
    }

    @Test
    void yearWeekPartition_resolvesCorrectly() {
        var strategy = new YearWeekPartition();
        var path = strategy.resolve("mutual-fund", "SIP_MUTUAL_FUND", BACKUP_TIME);
        assertThat(path.toString()).contains("mutual-fund/sip-mutual-fund/2026/W");
        assertThat(path.getFileName().toString()).endsWith(".parquet");
    }

    @Test
    void dailyPartition_resolvesCorrectly() {
        var strategy = new DailyPartition();
        var path = strategy.resolve("mutual-fund", "SIP_MUTUAL_FUND", BACKUP_TIME);
        assertThat(path.toString()).contains("mutual-fund/sip-mutual-fund/2026/03/14/");
        assertThat(path.getFileName().toString()).endsWith(".parquet");
    }

    @Test
    void flatPartition_resolvesCorrectly() {
        var strategy = new FlatPartition();
        var path = strategy.resolve("mutual-fund", "SIP_MUTUAL_FUND", BACKUP_TIME);
        assertThat(path.toString()).startsWith("mutual-fund/sip-mutual-fund/sip-mutual-fund-");
        assertThat(path.getFileName().toString()).endsWith(".parquet");
        // Flat: no year/month subdirectories
        assertThat(path.getParent().getFileName().toString()).isEqualTo("sip-mutual-fund");
    }
}
