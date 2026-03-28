package com.github.avivijay19.lifeledger.commons.backup.sheets;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public final class SheetTypeConverter {

    private SheetTypeConverter() {
    }

    @SuppressWarnings("unchecked")
    public static Object convert(String value, Class<?> targetType) {
        if (value == null || "null".equals(value)) {
            return null;
        }
        if (targetType == String.class) {
            return value;
        }
        if (targetType == UUID.class) {
            return UUID.fromString(value);
        }
        if (targetType == BigDecimal.class) {
            return new BigDecimal(value);
        }
        if (targetType == LocalDate.class) {
            return LocalDate.parse(value);
        }
        if (targetType == LocalDateTime.class) {
            return LocalDateTime.parse(value);
        }
        if (targetType == Instant.class) {
            return Instant.parse(value);
        }
        if (targetType == Integer.class || targetType == int.class) {
            return Integer.parseInt(value);
        }
        if (targetType == Long.class || targetType == long.class) {
            return Long.parseLong(value);
        }
        if (targetType == Double.class || targetType == double.class) {
            return Double.parseDouble(value);
        }
        if (targetType == Boolean.class || targetType == boolean.class) {
            return Boolean.parseBoolean(value);
        }
        if (targetType.isEnum()) {
            return Enum.valueOf((Class<Enum>) targetType, value);
        }
        throw new IllegalArgumentException(
            "Unsupported type: %s".formatted(targetType.getName()));
    }
}
