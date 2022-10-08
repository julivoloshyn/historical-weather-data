package com.knubisoft.weatherdata.parser.jsontotable;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ParserToTable {

    /**
     * Fills model list.
     *
     * @param inputSource Connection.
     * @param cls Unknown class.
     * @param <T> Unknown type.
     * @return Model list.
     */
    public <T> List<T> readAllValues(String location, String inputSource, Class<T> cls) {
        Table table = new JsonStrategy().parseToTable(inputSource, location);

        return convertTableToList(table, cls);
    }

    /**
     * Fills values into model fields.
     *
     * @param table Table - Map(key, value).
     * @param cls Unknown class.
     * @param <T> Unknown type.
     * @return Model list.
     */
    private   <T> List<T> convertTableToList(Table table, Class<T> cls) {
        List<T> resultList = new ArrayList<>();

        for (int i = 0; i < table.size(); i++) {
            Map<String, String> row = table.getTableRowByIndex(i);
            T rowToType = reflectTableRowToClass(row, cls);
            resultList.add(rowToType);
        }
        return resultList;
    }

    /**
     * Sets up values into model class.
     *
     * @param row Key, value.
     * @param cls Unknown class.
     * @param <T> Unknown type.
     * @return Cls instance.
     */
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

    /**
     * Fills values into fields.
     *
     * @param field From unknown class.
     * @param value To fill field.
     * @return Required object.
     */
    private Object transformValueToFieldType(Field field, String value) {
        Map<Class<?>, Function<String, Object>> typeToFunction = new LinkedHashMap<>();
        typeToFunction.put(String.class, s -> s);
        typeToFunction.put(double.class, Double::parseDouble);

        return typeToFunction.getOrDefault(field.getType(), type -> {
            throw new UnsupportedOperationException("Type  is not supported: " + type);
        }).apply(value);
    }
}
