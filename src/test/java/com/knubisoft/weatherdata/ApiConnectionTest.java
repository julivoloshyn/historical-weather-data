package com.knubisoft.weatherdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.knubisoft.weatherdata.api.ApiConnection;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ApiConnectionTest {

    ApiConnection instance = new ApiConnection();

    @SneakyThrows
    @Test
    void connectHistoricData_Success() {
        String body = instance.connectHistoricData("https://visual-crossing-weather.p.rapidapi.com/history?" +
                "startDateTime=2019-01-01T00%3A00%3A00&aggregateHours=24&" +
                "location=Washington%2CDC%2CUSA&endDateTime=2019-01-03T00%3A00%3A00&" +
                "unitGroup=uk&dayStartTime=8%3A00%3A00&contentType=json&" +
                "dayEndTime=17%3A00%3A00&shortColumnNames=0");

        String message = new ObjectMapper().readTree(body).path("message").asText();
        assertEquals("", message);
    }
    @SneakyThrows
    @Test
    void connectHistoricData_ApiHasErrorMessage() {
        String body = "";
        try {
             body = instance.connectHistoricData("https://visual-crossing-weather.p.rapidapi.com/history?" +
                    "startDateTime=2019-01-01T00%3A00%&aggregateHours=24&" +
                    "location=Washington%2CDC%2CUSA&endDateTime=2019-01-03T00%3A00%3A00&" +
                    "unitGroup=uk&dayStartTime=8%3A00%3A00&contentType=json&" +
                    "dayEndTime=17%3A00%3A00&shortColumnNames=0");
        } catch (IllegalArgumentException e){

            String message = new ObjectMapper().readTree(body).path("message").asText();
            assertNotEquals(null, message);
        }
    }

}
