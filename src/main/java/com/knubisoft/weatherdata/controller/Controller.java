package com.knubisoft.weatherdata.controller;

import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
public class Controller {

    @SneakyThrows
    @GetMapping("/forecast")
    public void forecast() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://visual-crossing-weather.p.rapidapi.com/forecast?aggregateHours=24&location=Washington%2CDC%2CUSA&contentType=json&unitGroup=us&shortColumnNames=0"))
                .header("X-RapidAPI-Key", "e383515f77mshb40c1345863fa6ep18c8fdjsndf718dcb823e")
                .header("X-RapidAPI-Host", "visual-crossing-weather.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        FileWriter file = new FileWriter("forecast.json");
        file.write(response.body());
        file.close();
    }

    @SneakyThrows
    @GetMapping("/history")
    public void historicData() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://visual-crossing-weather.p.rapidapi.com/history?startDateTime=2019-01-01T00%3A00%3A00&aggregateHours=24&location=Washington%2CDC%2CUSA&endDateTime=2019-01-03T00%3A00%3A00&unitGroup=us&dayStartTime=8%3A00%3A00&contentType=csv&dayEndTime=17%3A00%3A00&shortColumnNames=0"))
                .header("X-RapidAPI-Key", "e383515f77mshb40c1345863fa6ep18c8fdjsndf718dcb823e")
                .header("X-RapidAPI-Host", "visual-crossing-weather.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

}
