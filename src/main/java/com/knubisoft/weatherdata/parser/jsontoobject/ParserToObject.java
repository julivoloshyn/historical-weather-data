package com.knubisoft.weatherdata.parser.jsontoobject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knubisoft.weatherdata.weatherdto.WeatherNamings;
import lombok.SneakyThrows;

public class ParserToObject {

    public WeatherNamings readAllNamesOrUnits(String inputSource) {
        return parseToObject(inputSource);
    }

    @SneakyThrows
    public WeatherNamings parseToObject(String content) {
        return new ObjectMapper().readValue(content, WeatherNamings.class);
    }

}
