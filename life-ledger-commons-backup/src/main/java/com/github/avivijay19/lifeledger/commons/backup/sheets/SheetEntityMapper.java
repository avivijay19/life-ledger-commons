package com.github.avivijay19.lifeledger.commons.backup.sheets;

import jakarta.persistence.Column;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class SheetEntityMapper {

    private SheetEntityMapper() {
    }

    public static List<String> getColumnNames(Class<?> entityClass) {
        var columns = new ArrayList<String>();
        for (var field : entityClass.getDeclaredFields()) {
            var column = field.getAnnotation(Column.class);
            if (column != null) {
                columns.add(column.name());
            }
        }
        return columns;
    }

    public static List<Object> getColumnHeaders(Class<?> entityClass) {
        return new ArrayList<>(getColumnNames(entityClass));
    }

    public static <T> List<Map<String, String>> getTableData(List<T> entities) {
        if (entities.isEmpty()) {
            return List.of();
        }
        return entities.stream().map(SheetEntityMapper::toRow).toList();
    }

    public static List<Map<String, String>> rowsToMaps(List<List<Object>> rows) {
        if (rows == null || rows.size() <= 1) {
            return List.of();
        }
        var headers = rows.getFirst();
        var result = new ArrayList<Map<String, String>>();
        for (int i = 1; i < rows.size(); i++) {
            var row = rows.get(i);
            var map = new LinkedHashMap<String, String>();
            for (int j = 0; j < headers.size(); j++) {
                var value = j < row.size() ? row.get(j) : null;
                map.put(headers.get(j).toString(), value != null ? value.toString() : null);
            }
            result.add(map);
        }
        return result;
    }

    public static <T> T mapToEntity(Map<String, String> row, Class<T> entityClass) {
        try {
            var entity = entityClass.getDeclaredConstructor().newInstance();
            for (Field field : entityClass.getDeclaredFields()) {
                var column = field.getAnnotation(Column.class);
                if (column == null) {
                    continue;
                }
                var value = row.get(column.name());
                if (value == null || "null".equals(value)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(entity, SheetTypeConverter.convert(value, field.getType()));
            }
            return entity;
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(
                "Failed to map row to entity: " + entityClass.getSimpleName(), e);
        }
    }

    private static <T> Map<String, String> toRow(T entity) {
        var row = new LinkedHashMap<String, String>();
        for (var field : entity.getClass().getDeclaredFields()) {
            var column = field.getAnnotation(Column.class);
            if (column == null) {
                continue;
            }
            field.setAccessible(true);
            try {
                row.put(column.name(), String.valueOf(field.get(entity)));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to read field: " + field.getName(), e);
            }
        }
        return row;
    }
}
