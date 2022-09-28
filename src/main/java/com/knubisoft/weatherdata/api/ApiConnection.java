package com.knubisoft.weatherdata.api;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConnection {

    @SneakyThrows
    public String forecast() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://visual-crossing-weather.p.rapidapi.com/forecast?aggregateHours=24&location=Washington%2CDC%2CUSA&contentType=json&unitGroup=us&shortColumnNames=0"))
                .header("X-RapidAPI-Key", "e383515f77mshb40c1345863fa6ep18c8fdjsndf718dcb823e")
                .header("X-RapidAPI-Host", "visual-crossing-weather.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    @SneakyThrows
    public String historicData(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("X-RapidAPI-Key", "e383515f77mshb40c1345863fa6ep18c8fdjsndf718dcb823e")
                .header("X-RapidAPI-Host", "visual-crossing-weather.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
