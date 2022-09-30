package com.knubisoft.weatherdata.controller;

import com.knubisoft.weatherdata.api.ApiConnection;
import com.knubisoft.weatherdata.parser.jsontotable.ParserToTable;
import com.knubisoft.weatherdata.requestdto.HistoricDataParams;
import com.knubisoft.weatherdata.table.TableProcessor;
import com.knubisoft.weatherdata.weatherdto.WeatherValues;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    ApiConnection apiConnection = new ApiConnection();
    TableProcessor tableProcessor = new TableProcessor();

    @SneakyThrows
    @GetMapping("/historic-data")
    public void historicData(@RequestBody HistoricDataParams historicDataParams) {

        String responseBody = apiConnection.historicData(
                "https://visual-crossing-weather.p.rapidapi.com/history?" +
                "startDateTime=" + historicDataParams.getStartDateTime() +
                "&aggregateHours=" + historicDataParams.getAggregateHours() +
                "&location=" + historicDataParams.getLocation() +
                "&endDateTime=" + historicDataParams.getEndDateTime() +
                "&unitGroup=metric" +
                "&contentType=json"
        );

        List<WeatherValues> valuesList = new ParserToTable().readAllValues(
                historicDataParams.getLocation(), responseBody, WeatherValues.class);

        tableProcessor.createPdf(
                valuesList,
                historicDataParams.getStartDateTime(),
                historicDataParams.getEndDateTime());
    }

}
