package com.knubisoft.weatherdata.api;

import lombok.SneakyThrows;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConnection {

    /**
     * Creates connection with api.
     *
     * @param uri Request for API.
     * @return response.
     */
    @SneakyThrows
    public String connectHistoricData(String uri) {
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
