package com.knubisoft.weatherdata.parser.jsontoobject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knubisoft.weatherdata.controller.location.LocationDetailsData;
import com.knubisoft.weatherdata.table.tabledto.WeatherNamings;
import lombok.SneakyThrows;

public class ParserToObject {

    /**
     * Parses values from json files of names and units to object.
     *
     * @param inputSource Request body.
     * @return Object of namings.
     */
    @SneakyThrows
    public WeatherNamings readNamesOrUnits(String inputSource) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputSource, WeatherNamings.class);
    }

    /**
     * Reads details about location from API response.
     *
     * @param inputSource Request body.
     * @param location location from request.
     * @return Detailed location.
     */
    @SneakyThrows
    public LocationDetailsData readLocationDetails(String inputSource, String location) {
        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode valuesNode = objectMapper
                .readTree(inputSource)
                .path("locations")
                .path(location);

        return new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .treeToValue(valuesNode, LocationDetailsData.class);
    }
}
