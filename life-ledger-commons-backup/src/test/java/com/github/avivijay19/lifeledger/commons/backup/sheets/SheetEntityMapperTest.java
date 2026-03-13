package com.github.avivijay19.lifeledger.commons.backup.sheets;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class SheetEntityMapperTest {

    @Test
    void rowsToMaps_convertsCorrectly() {
        var header = List.<Object>of("uuid", "scheme_code", "amount");
        var row1 = List.<Object>of("abc-123", "119551", "1000.0");
        var row2 = List.<Object>of("def-456", "120503", "2500.0");

        var rows = List.of(header, row1, row2);
        var result = SheetEntityMapper.rowsToMaps(rows);

        assertThat(result).hasSize(2);
        assertThat(result.get(0).get("uuid")).isEqualTo("abc-123");
        assertThat(result.get(0).get("scheme_code")).isEqualTo("119551");
        assertThat(result.get(1).get("amount")).isEqualTo("2500.0");
    }

    @Test
    void rowsToMaps_emptyData_returnsEmptyList() {
        assertThat(SheetEntityMapper.rowsToMaps(null)).isEmpty();
        assertThat(SheetEntityMapper.rowsToMaps(List.of())).isEmpty();
        assertThat(SheetEntityMapper.rowsToMaps(
            List.of(List.<Object>of("header")))).isEmpty();
    }

    @Test
    void rowsToMaps_handlesShortRows() {
        var header = List.<Object>of("a", "b", "c");
        var shortRow = List.<Object>of("val1");
        var result = SheetEntityMapper.rowsToMaps(List.of(header, shortRow));

        assertThat(result).hasSize(1);
        assertThat(result.get(0).get("a")).isEqualTo("val1");
        assertThat(result.get(0).get("b")).isNull();
        assertThat(result.get(0).get("c")).isNull();
    }
}
