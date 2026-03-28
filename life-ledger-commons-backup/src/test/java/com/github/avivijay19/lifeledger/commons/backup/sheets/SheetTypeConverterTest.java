package com.github.avivijay19.lifeledger.commons.backup.sheets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class SheetTypeConverterTest {

    @Test
    void shouldConvertString() {
        assertThat(SheetTypeConverter.convert("hello", String.class))
            .isEqualTo("hello");
    }

    @Test
    void shouldConvertUuid() {
        var uuid = UUID.randomUUID();
        assertThat(SheetTypeConverter.convert(uuid.toString(), UUID.class))
            .isEqualTo(uuid);
    }

    @Test
    void shouldConvertBigDecimal() {
        assertThat((BigDecimal) SheetTypeConverter.convert("123.45", BigDecimal.class))
            .isEqualByComparingTo(new BigDecimal("123.45"));
    }

    @Test
    void shouldConvertLocalDate() {
        assertThat(SheetTypeConverter.convert("2026-03-21", LocalDate.class))
            .isEqualTo(LocalDate.of(2026, 3, 21));
    }

    @Test
    void shouldConvertLocalDateTime() {
        assertThat(SheetTypeConverter.convert(
            "2026-03-21T10:30:00", LocalDateTime.class))
            .isEqualTo(LocalDateTime.of(2026, 3, 21, 10, 30, 0));
    }

    @Test
    void shouldConvertInstant() {
        var instant = Instant.parse("2026-03-21T10:30:00Z");
        assertThat(SheetTypeConverter.convert("2026-03-21T10:30:00Z", Instant.class))
            .isEqualTo(instant);
    }

    @Test
    void shouldConvertInteger() {
        assertThat(SheetTypeConverter.convert("42", Integer.class)).isEqualTo(42);
        assertThat(SheetTypeConverter.convert("42", int.class)).isEqualTo(42);
    }

    @Test
    void shouldConvertLong() {
        assertThat(SheetTypeConverter.convert("100", Long.class)).isEqualTo(100L);
    }

    @Test
    void shouldConvertDouble() {
        assertThat(SheetTypeConverter.convert("3.14", Double.class)).isEqualTo(3.14);
        assertThat(SheetTypeConverter.convert("3.14", double.class)).isEqualTo(3.14);
    }

    @Test
    void shouldConvertBoolean() {
        assertThat((Boolean) SheetTypeConverter.convert("true", Boolean.class)).isTrue();
        assertThat((Boolean) SheetTypeConverter.convert("false", boolean.class)).isFalse();
    }

    @Test
    void shouldConvertEnum() {
        assertThat(SheetTypeConverter.convert("SECONDS",
            java.util.concurrent.TimeUnit.class))
            .isEqualTo(java.util.concurrent.TimeUnit.SECONDS);
    }

    @Test
    void shouldReturnNullForNullOrNullString() {
        assertThat(SheetTypeConverter.convert(null, String.class)).isNull();
        assertThat(SheetTypeConverter.convert("null", UUID.class)).isNull();
        assertThat(SheetTypeConverter.convert("null", Integer.class)).isNull();
    }

    @Test
    void shouldThrowForUnsupportedType() {
        assertThatThrownBy(() ->
            SheetTypeConverter.convert("test", java.io.File.class))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Unsupported type");
    }
}
