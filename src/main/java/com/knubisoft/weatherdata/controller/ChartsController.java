package com.knubisoft.weatherdata.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knubisoft.weatherdata.api.ApiConnection;
import com.knubisoft.weatherdata.chart.resultchart.impl.ResultChartImpl;
import com.knubisoft.weatherdata.controller.location.LocationDetailsData;
import com.knubisoft.weatherdata.parser.jsontoobject.ParserToObject;
import com.knubisoft.weatherdata.parser.jsontotable.ParserToTable;
import com.knubisoft.weatherdata.requestdto.HistoricDataParams;
import com.knubisoft.weatherdata.chart.chartdto.HumidityChangesData;
import com.knubisoft.weatherdata.chart.chartdto.PrecipitationChangesData;
import com.knubisoft.weatherdata.chart.chartdto.TemperatureChangesData;
import io.swagger.annotations.*;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Validated
@RestController
@RequestMapping("/chart")
public class ChartsController {

    ApiConnection apiConnection = new ApiConnection();
    ResultChartImpl changesChart = new ResultChartImpl();

    /**
     * Creates a chart of historic temperature data in PDF format.
     * Returns detailed location and path name of .pdf document.
     * In case of API error returns a message of it.
     *
     * @param historicDataParams (startDateTime, location, endDateTime).
     * @return List of info.
     */
    @ApiOperation(value = "Create a temperature chart by entered parameters", notes = "Returns detailed location and path name of PDF document.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 404, message = "Incorrect parameters. Weather API cannot processes ones."),
            @ApiResponse(code = 500, message = "An unexpected error has occurred.")

    })
    @SneakyThrows
    @PostMapping("/temperature-changes")
    public ResponseEntity<List<Object>> buildChartOfHistoricTemperatureChanges(
            @Valid @RequestBody HistoricDataParams historicDataParams) {

        String responseBody = apiConnection.connectHistoricData(
                "https://visual-crossing-weather.p.rapidapi.com/history?" +
                        "startDateTime=" + historicDataParams.getStartDateTime() +
                        "T00%3A00%3A00&aggregateHours=24&location=" + historicDataParams.getLocation() +
                        "&endDateTime=" + historicDataParams.getEndDateTime() +
                        "T00%3A00%3A00&unitGroup=metric&contentType=json"
        );

        JsonNode node = new ObjectMapper().readTree(responseBody);
        if (node.path("executionTime").asText().equals("-1")) {
            return ResponseEntity.ok(List.of(node.path("message").asText()));
        }

        List<TemperatureChangesData> weatherList = new ParserToTable().readAllValues(
                historicDataParams.getLocation(), responseBody, TemperatureChangesData.class);

        String path = changesChart.buildResult(weatherList,
                historicDataParams.getStartDateTime(),
                historicDataParams.getEndDateTime());

        List<Object> resultBody = Arrays.asList(
                new ParserToObject().readLocationDetails(responseBody,
                        historicDataParams.getLocation()), path);

        return ResponseEntity.ok(resultBody);
    }

    /**
     * Creates a chart of historic precipitation data in PDF format.
     * Returns detailed location and path name of .pdf document.
     * In case of API error returns a message of it.
     *
     * @param historicDataParams (startDateTime, location, endDateTime).
     * @return List of info.
     */
    @ApiOperation(value = "Create a precipitation chart by entered parameters", notes = "Returns detailed location and path name of PDF document.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 404, message = "Incorrect parameters. Weather API cannot processes ones."),
            @ApiResponse(code = 500, message = "An unexpected error has occurred.")

    })
    @SneakyThrows
    @PostMapping("/precipitation-changes")
    public ResponseEntity<List<Object>> buildChartOfHistoricPrecipitationChanges(
            @Valid @RequestBody HistoricDataParams historicDataParams) {

        String responseBody = apiConnection.connectHistoricData(
                "https://visual-crossing-weather.p.rapidapi.com/history?" +
                        "startDateTime=" + historicDataParams.getStartDateTime() +
                        "T00%3A00%3A00&aggregateHours=24&location=" + historicDataParams.getLocation() +
                        "&endDateTime=" + historicDataParams.getEndDateTime() +
                        "T00%3A00%3A00&unitGroup=metric&contentType=json"
        );

        JsonNode node = new ObjectMapper().readTree(responseBody);
        if (node.path("executionTime").asText().equals("-1")) {
            return ResponseEntity.ok(List.of(node.path("message").asText()));
        }

        List<PrecipitationChangesData> weatherList = new ParserToTable().readAllValues(
                historicDataParams.getLocation(), responseBody, PrecipitationChangesData.class);

        String path = changesChart.buildResult(weatherList,
                historicDataParams.getStartDateTime(),
                historicDataParams.getEndDateTime());

        List<Object> resultBody = Arrays.asList(
                new ParserToObject().readLocationDetails(responseBody,
                        historicDataParams.getLocation()), path);

        return ResponseEntity.ok(resultBody);
    }

    /**
     * Creates a chart of historic humidity data in PDF format.
     * Returns detailed location and path name of .pdf document.
     * In case of API error returns a message of it.
     *
     * @param historicDataParams (startDateTime, location, endDateTime).
     * @return List of info.
     */
    @ApiOperation(value = "Create a humidity chart by entered parameters", notes = "Returns detailed location and path name of PDF document.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created"),
            @ApiResponse(code = 404, message = "Incorrect parameters. Weather API cannot processes ones."),
            @ApiResponse(code = 500, message = "An unexpected error has occurred.")

    })
    @SneakyThrows
    @PostMapping("/humidity-changes")
    public ResponseEntity<Object> buildChartOfHistoricHumidityChanges(
            @Valid @RequestBody HistoricDataParams historicDataParams) {

        String responseBody = apiConnection.connectHistoricData(
                "https://visual-crossing-weather.p.rapidapi.com/history?" +
                        "startDateTime=" + historicDataParams.getStartDateTime() +
                        "T00%3A00%3A00&aggregateHours=24&location=" + historicDataParams.getLocation() +
                        "&endDateTime=" + historicDataParams.getEndDateTime() +
                        "T00%3A00%3A00&unitGroup=metric&contentType=json"
        );

        JsonNode node = new ObjectMapper().readTree(responseBody);
        if (node.path("executionTime").asText().equals("-1")) {
            return ResponseEntity.ok(List.of(node.path("message").asText()));
        }

        List<HumidityChangesData> weatherList = new ParserToTable().readAllValues(
                historicDataParams.getLocation(), responseBody, HumidityChangesData.class);

        String path = changesChart.buildResult(weatherList,
                historicDataParams.getStartDateTime(),
                historicDataParams.getEndDateTime());

        List<Object> resultBody = Arrays.asList(
                new ParserToObject().readLocationDetails(responseBody,
                        historicDataParams.getLocation()), path);

        return ResponseEntity.ok(resultBody);
    }

}
