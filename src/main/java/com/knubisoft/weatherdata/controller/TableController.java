package com.knubisoft.weatherdata.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.knubisoft.weatherdata.api.ApiConnection;
import com.knubisoft.weatherdata.parser.jsontoobject.ParserToObject;
import com.knubisoft.weatherdata.parser.jsontotable.ParserToTable;
import com.knubisoft.weatherdata.requestdto.HistoricDataParams;
import com.knubisoft.weatherdata.table.tablebuilder.TableBuilder;
import com.knubisoft.weatherdata.table.tabledto.WeatherValues;
import io.swagger.annotations.*;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@Validated
public class TableController {

    ApiConnection apiConnection = new ApiConnection();
    TableBuilder tableBuilder = new TableBuilder();

    /**
     * Creates a table in PDF format. Returns detailed location and path name of .pdf document.
     * In case of API error returns a message of it.
     *
     * @param historicDataParams (startDateTime, location, endDateTime).
     * @return List of info.
     */
    @SneakyThrows
    @ApiOperation(value = "Create a table by entered parameters", notes = "Returns detailed location and path name of PDF document.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created", examples = @Example({@ExampleProperty(
                    value = "[{hello: hola}, hola: hello]",
                    mediaType = "application/json"
            )})),
            @ApiResponse(code = 404, message = "Incorrect parameters. Weather API cannot processes ones."),
            @ApiResponse(code = 500, message = "An unexpected error has occurred.")

    })
    @PostMapping("/table")
    public ResponseEntity<List<Object>> buildTableOfHistoricData(@Valid @RequestBody HistoricDataParams historicDataParams) {

        ResponseEntity<List<Object>> result;

        String responseBody = apiConnection.connectHistoricData(
                "https://visual-crossing-weather.p.rapidapi.com/history?" +
                        "startDateTime=" + historicDataParams.getStartDateTime() +
                        "T00%3A00%3A00&aggregateHours=24&location=" + historicDataParams.getLocation() +
                        "&endDateTime=" + historicDataParams.getEndDateTime() +
                        "T00%3A00%3A00&unitGroup=metric&contentType=json"
        );


        JsonNode node = new ObjectMapper().readTree(responseBody);
        if (node.path("executionTime").asText().equals("-1")) {
            result = ResponseEntity.ok(List.of(node.path("message").asText()));
        } else {
            List<WeatherValues> valuesList = new ParserToTable().readAllValues(
                    historicDataParams.getLocation(), responseBody, WeatherValues.class);
            String path = tableBuilder.generatePdf(
                    valuesList,
                    historicDataParams.getStartDateTime(),
                    historicDataParams.getEndDateTime());
            List<Object> resultBody = Arrays.asList(
                    new ParserToObject().readLocationDetails(responseBody,
                            historicDataParams.getLocation()), path);
            result = ResponseEntity.ok(resultBody);
        }

        return result;
    }

}
