package com.knubisoft.weatherdata.parser.jsontotable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonStrategy {

    /**
     * Builds new Table.
     *
     * @param content File.
     * @return Table - Map(key, value).
     */
    @SneakyThrows
    public Table parseToTable(String content, String location) {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode valuesNode = objectMapper
                .readTree(content)
                .path("locations")
                .path(location)
                .path("values");

        Map<Integer, Map<String, String>> mapFromTreeNode = buildTable(valuesNode);
        return new Table(mapFromTreeNode);
    }

    /**
     * Builds filled map with values from json file.
     *
     * @param treeNode Array json node.
     * @return Map (key, Map(key, value)).
     */
    private Map<Integer, Map<String, String>> buildTable(JsonNode treeNode) {
        Map<Integer, Map<String, String>> map = new LinkedHashMap<>();
        int index = 0;
        for (JsonNode node : treeNode) {
            Map<String, String> rowMap = buildRow(node);
            map.put(index, rowMap);
            index++;
        }
        return map;
    }

    /**
     * Puts each row from json file to a map.
     *
     * @param node Row in json file.
     * @return Map (key, value).
     */
    private Map<String, String> buildRow(JsonNode node) {
        Map<String, String> rowMap = new LinkedHashMap<>();
        Iterator<Map.Entry<String, JsonNode>> iterator = node.fields();
        while (iterator.hasNext()) {
            Map.Entry<String, JsonNode> nextNode = iterator.next();
            rowMap.put(nextNode.getKey(), nextNode.getValue().asText());
        }
        return rowMap;
    }

}
