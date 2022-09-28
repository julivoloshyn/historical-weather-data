package com.knubisoft.weatherdata.jsonparser;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Parser {

    JsonParsingStrategy jsonParsingStrategy = new JsonParsingStrategy();

    public <T> List<T> readAll(String location, String inputSource, Class<T> cls) {
        Table table = jsonParsingStrategy.parseToTable(inputSource, location);

        return convertTableToList(table, cls);
    }

    private <T> List<T> convertTableToList(Table table, Class<T> cls) {
        List<T> resultList = new ArrayList<>();

        for (int i = 0; i < table.size(); i++) {
            Map<String, String> row = table.getTableRowByIndex(i);
            T rowToType = reflectTableRowToClass(row, cls);
            resultList.add(rowToType);
        }
        return resultList;
    }

    @SneakyThrows
    private <T> T reflectTableRowToClass(Map<String, String> row, Class<T> cls) {
        T toType = cls.getDeclaredConstructor().newInstance();

        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            String rowValue = row.get(field.getName());
            if (rowValue != null) {
                field.set(toType, transformValueToFieldType(field, rowValue.trim()));
            }
        }
        return toType;
    }

    private Object transformValueToFieldType(Field field, String value) {
        Map<Class<?>, Function<String, Object>> typeToFunction = new LinkedHashMap<>();
        typeToFunction.put(String.class, s -> s);
        typeToFunction.put(double.class, Double::parseDouble);

        return typeToFunction.getOrDefault(field.getType(), type -> {
            throw new UnsupportedOperationException("Type  is not supported: " + type);
        }).apply(value);
    }
}
