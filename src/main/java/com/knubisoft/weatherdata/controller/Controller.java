package com.knubisoft.weatherdata.controller;

import com.knubisoft.weatherdata.api.ApiConnection;
import com.knubisoft.weatherdata.chartbuilder.TemperatureChangesChart;
import com.knubisoft.weatherdata.jsonparser.Parser;
import com.knubisoft.weatherdata.requestdto.HistoricDataParams;
import com.knubisoft.weatherdata.weatherdto.FullWeatherData;
import com.knubisoft.weatherdata.weatherdto.SimpleWeatherData;
import com.knubisoft.weatherdata.weatherdto.TemperatureChangesData;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    TemperatureChangesChart pngChart = new TemperatureChangesChart();
    ApiConnection apiConnection = new ApiConnection();

    @GetMapping("/temperature-changes")
    public void historicTemperatureChanges(
            @RequestBody HistoricDataParams historicDataParams) {

        String responseBody = apiConnection.historicData(
                "https://visual-crossing-weather.p.rapidapi.com/history?" +
                        "startDateTime=" + historicDataParams.getStartDateTime() +
                        "&aggregateHours=" + historicDataParams.getAggregateHours() +
                        "&location=" + historicDataParams.getLocation() +
                        "&endDateTime=" + historicDataParams.getEndDateTime() +
                        "&unitGroup=" + historicDataParams.getUnitGroup() +
                        "&contentType=json"
        );

        List<TemperatureChangesData> weatherList = new Parser().readAll(
                historicDataParams.getLocation(), responseBody, TemperatureChangesData.class);

        pngChart.createChart(weatherList,
                historicDataParams.getUnitGroup(),
                historicDataParams.getStartDateTime(),
                historicDataParams.getEndDateTime());
    }

    @GetMapping("/historic-simple-data")
    public ResponseEntity<List<SimpleWeatherData>> historicSimpleData(
            @RequestBody HistoricDataParams historicDataParams) {

        String responseBody = apiConnection.historicData(
                "https://visual-crossing-weather.p.rapidapi.com/history?" +
                "startDateTime=" + historicDataParams.getStartDateTime() +
                "&aggregateHours=" + historicDataParams.getAggregateHours() +
                "&location=" + historicDataParams.getLocation() +
                "&endDateTime=" + historicDataParams.getEndDateTime() +
                "&unitGroup=" + historicDataParams.getUnitGroup() +
                "&contentType=json"
        );

        List<SimpleWeatherData> weatherList = new Parser().readAll(
                historicDataParams.getLocation(), responseBody, SimpleWeatherData.class);

        return ResponseEntity.ok(weatherList);
    }

    @GetMapping("/historic-full-data")
    public ResponseEntity<List<FullWeatherData>> historicFullData(
            @RequestBody HistoricDataParams historicDataParams) {

        String responseBody = apiConnection.historicData(
                "https://visual-crossing-weather.p.rapidapi.com/history?" +
                        "startDateTime=" + historicDataParams.getStartDateTime() +
                        "&aggregateHours=" + historicDataParams.getAggregateHours() +
                        "&location=" + historicDataParams.getLocation() +
                        "&endDateTime=" + historicDataParams.getEndDateTime() +
                        "&unitGroup=" + historicDataParams.getUnitGroup() +
                        "&contentType=json"
        );

        List<FullWeatherData> weatherList = new Parser().readAll(
                historicDataParams.getLocation(), responseBody, FullWeatherData.class);

        return ResponseEntity.ok(weatherList);
    }
}
