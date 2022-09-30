package com.knubisoft.weatherdata.controller;

import com.knubisoft.weatherdata.api.ApiConnection;
import com.knubisoft.weatherdata.chart.chageschart.impl.ChangesChartImpl;
import com.knubisoft.weatherdata.parser.jsontotable.ParserToTable;
import com.knubisoft.weatherdata.requestdto.HistoricDataParams;
import com.knubisoft.weatherdata.chart.chartdto.HumidityChangesData;
import com.knubisoft.weatherdata.chart.chartdto.PrecipitationChangesData;
import com.knubisoft.weatherdata.chart.chartdto.TemperatureChangesData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HistoryDataChartsController {

    ApiConnection apiConnection = new ApiConnection();
    ChangesChartImpl changesChart = new ChangesChartImpl();

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

        List<TemperatureChangesData> weatherList = new ParserToTable().readAllValues(
                historicDataParams.getLocation(), responseBody, TemperatureChangesData.class);

        changesChart.createChart(weatherList,
                historicDataParams.getStartDateTime(),
                historicDataParams.getEndDateTime());
    }

    @GetMapping("/precipitation-changes")
    public void historicPrecipitationChanges(
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

        List<PrecipitationChangesData> weatherList = new ParserToTable().readAllValues(
                historicDataParams.getLocation(), responseBody, PrecipitationChangesData.class);

        changesChart.createChart(weatherList,
                historicDataParams.getStartDateTime(),
                historicDataParams.getEndDateTime());
    }

    @GetMapping("/humidity-changes")
    public void historicHumidityChanges(
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

        List<HumidityChangesData> weatherList = new ParserToTable().readAllValues(
                historicDataParams.getLocation(), responseBody, HumidityChangesData.class);

        changesChart.createChart(weatherList,
                historicDataParams.getStartDateTime(),
                historicDataParams.getEndDateTime());
    }

}
