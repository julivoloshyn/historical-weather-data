package com.knubisoft.weatherdata.parser.jsontotable;

import lombok.RequiredArgsConstructor;

import java.util.LinkedHashMap;
import java.util.Map;

@RequiredArgsConstructor
public class Table {
    private final Map<Integer, Map<String, String>> table;

    int size() {
        return table.size();
    }

    /**
     * Gets row by index.
     *
     * @param row Some row from table.
     * @return Map(key, value).
     */
    Map<String, String> getTableRowByIndex(int row) {
        Map<String, String> tableRow = table.get(row);
        return tableRow == null ? null : new LinkedHashMap<>(tableRow);
    }

}
