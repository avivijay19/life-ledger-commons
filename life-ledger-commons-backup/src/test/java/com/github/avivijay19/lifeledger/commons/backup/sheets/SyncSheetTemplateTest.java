package com.github.avivijay19.lifeledger.commons.backup.sheets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.persistence.Column;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class SyncSheetTemplateTest {

    private final GoogleSheetClient client = mock(GoogleSheetClient.class);
    private final SyncSheetTemplate template = new SyncSheetTemplate(client);

    @SuppressWarnings("unused")
    static class TestEntity {
        @Column(name = "id")
        private UUID id;

        @Column(name = "name")
        private String name;

        public TestEntity() {
        }
    }

    @Test
    void syncToSheet_writesHeadersAndData() throws IOException {
        var entity = new TestEntity();
        entity.id = UUID.randomUUID();
        entity.name = "Test";

        template.syncToSheet("SHEET1", List.of(entity),
            TestEntity.class, List.of("id"));

        verify(client).clearSheet("SHEET1");
        verify(client).writeSheet(any(), eq("SHEET1"));
    }

    @Test
    void readEntitiesFromSheet_returnsEntities() throws IOException {
        var uuid = UUID.randomUUID();
        var header = List.<Object>of("id", "name");
        var row = List.<Object>of(uuid.toString(), "FromSheet");
        when(client.readSheet("SHEET1")).thenReturn(List.of(header, row));

        var entities = template.readEntitiesFromSheet("SHEET1", TestEntity.class);

        assertThat(entities).hasSize(1);
        assertThat(entities.get(0).id).isEqualTo(uuid);
        assertThat(entities.get(0).name).isEqualTo("FromSheet");
    }

    @Test
    void readEntitiesFromSheet_emptySheet_returnsEmptyList() throws IOException {
        when(client.readSheet("SHEET1")).thenReturn(null);

        var entities = template.readEntitiesFromSheet("SHEET1", TestEntity.class);
        assertThat(entities).isEmpty();
    }

    @Test
    void syncToSheet_wrapsIoException() throws IOException {
        doThrow(new IOException("Network error")).when(client).clearSheet("SHEET1");

        assertThatThrownBy(() ->
            template.syncToSheet("SHEET1", List.of(), TestEntity.class, List.of("id")))
            .isInstanceOf(UncheckedIOException.class)
            .hasMessageContaining("Failed to sync to sheet");
    }
}
