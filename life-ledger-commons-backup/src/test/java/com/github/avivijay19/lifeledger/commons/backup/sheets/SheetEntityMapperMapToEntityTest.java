package com.github.avivijay19.lifeledger.commons.backup.sheets;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class SheetEntityMapperMapToEntityTest {

    @SuppressWarnings("unused")
    static class SampleEntity {
        @Column(name = "id")
        private UUID id;

        @Column(name = "name")
        private String name;

        @Column(name = "amount")
        private BigDecimal amount;

        @Column(name = "trade_date")
        private LocalDate tradeDate;

        @Column(name = "active")
        private boolean active;

        private String notMapped;

        public SampleEntity() {
        }
    }

    @Test
    void shouldMapRowToEntity() {
        var uuid = UUID.randomUUID();
        var row = Map.of(
            "id", uuid.toString(),
            "name", "Test",
            "amount", "100.50",
            "trade_date", "2026-03-21",
            "active", "true"
        );

        var entity = SheetEntityMapper.mapToEntity(row, SampleEntity.class);

        assertThat(entity.id).isEqualTo(uuid);
        assertThat(entity.name).isEqualTo("Test");
        assertThat(entity.amount).isEqualByComparingTo(new BigDecimal("100.50"));
        assertThat(entity.tradeDate).isEqualTo(LocalDate.of(2026, 3, 21));
        assertThat(entity.active).isTrue();
        assertThat(entity.notMapped).isNull();
    }

    @Test
    void shouldHandleNullValues() {
        var row = Map.of("id", UUID.randomUUID().toString(), "name", "null");
        var entity = SheetEntityMapper.mapToEntity(row, SampleEntity.class);

        assertThat(entity.name).isNull();
    }

    @Test
    void shouldHandleMissingColumns() {
        var row = Map.of("name", "OnlyName");
        var entity = SheetEntityMapper.mapToEntity(row, SampleEntity.class);

        assertThat(entity.name).isEqualTo("OnlyName");
        assertThat(entity.id).isNull();
        assertThat(entity.amount).isNull();
    }

    @Test
    void shouldRoundTripEntityThroughSheetAndBack() {
        var uuid = UUID.randomUUID();
        var original = new SampleEntity();
        original.id = uuid;
        original.name = "RoundTrip";
        original.amount = new BigDecimal("999.99");
        original.tradeDate = LocalDate.of(2026, 1, 15);
        original.active = true;

        var tableData = SheetEntityMapper.getTableData(List.of(original));
        var restored = SheetEntityMapper.mapToEntity(tableData.get(0), SampleEntity.class);

        assertThat(restored.id).isEqualTo(uuid);
        assertThat(restored.name).isEqualTo("RoundTrip");
        assertThat(restored.amount).isEqualByComparingTo(new BigDecimal("999.99"));
        assertThat(restored.tradeDate).isEqualTo(LocalDate.of(2026, 1, 15));
        assertThat(restored.active).isTrue();
    }

    @Test
    void shouldMapRowsToMapsAndThenToEntities() {
        var uuid = UUID.randomUUID();
        var header = List.<Object>of("id", "name", "amount", "trade_date", "active");
        var row = List.<Object>of(uuid.toString(), "Test", "50.0", "2026-06-01", "false");
        var rows = List.of(header, row);

        var maps = SheetEntityMapper.rowsToMaps(rows);
        var entity = SheetEntityMapper.mapToEntity(maps.get(0), SampleEntity.class);

        assertThat(entity.id).isEqualTo(uuid);
        assertThat(entity.name).isEqualTo("Test");
        assertThat(entity.amount).isEqualByComparingTo(new BigDecimal("50.0"));
        assertThat(entity.active).isFalse();
    }
}
