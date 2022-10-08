package com.knubisoft.weatherdata;

import com.knubisoft.weatherdata.controller.location.LocationDetailsData;
import com.knubisoft.weatherdata.parser.jsontoobject.ParserToObject;
import com.knubisoft.weatherdata.table.tabledto.WeatherNamings;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserJsonToObjectTest {

    ParserToObject instance = new ParserToObject();

    @SneakyThrows
    @Test
    void readNames_Success() {
        String inputSource = FileUtils.readFileToString(
                new File("src/test/resources/jsonparsing/names.json"),
                StandardCharsets.UTF_8);

        WeatherNamings namings = new WeatherNamings();
        namings.setMaxt("Maximum temperature");
        namings.setMint("Minimum temperature");
        namings.setHumidity("Humidity");
        namings.setConditions("Conditions");
        namings.setWeathertype("Weather type");
        namings.setWspd("Wind speed");
        namings.setPresipсover("Precipitation cover");
        namings.setSolarradiation("Solar radiation");
        namings.setVisibility("Visibility");

        assertEquals(namings.getConditions(), instance.readNamesOrUnits(inputSource).getConditions());
        assertEquals(namings.getMaxt(), instance.readNamesOrUnits(inputSource).getMaxt());
        assertEquals(namings.getWeathertype(), instance.readNamesOrUnits(inputSource).getWeathertype());
        assertEquals(namings.getVisibility(), instance.readNamesOrUnits(inputSource).getVisibility());
        assertEquals(namings.getMint(), instance.readNamesOrUnits(inputSource).getMint());
        assertEquals(namings.getPresipсover(), instance.readNamesOrUnits(inputSource).getPresipсover());
    }

    @SneakyThrows
    @Test
    void readUnits_Success() {
        String inputSource = FileUtils.readFileToString(
                new File("src/test/resources/jsonparsing/units.json"),
                StandardCharsets.UTF_8);

        WeatherNamings namings = new WeatherNamings();
        namings.setMaxt("degC");
        namings.setMint("degC");
        namings.setHumidity("%");
        namings.setConditions("");
        namings.setWeathertype("");
        namings.setWspd("kph");
        namings.setPresipсover("%");
        namings.setSolarradiation("W/m^2");
        namings.setVisibility("km");

        assertEquals(namings.getConditions(), instance.readNamesOrUnits(inputSource).getConditions());
        assertEquals(namings.getMaxt(), instance.readNamesOrUnits(inputSource).getMaxt());
        assertEquals(namings.getWeathertype(), instance.readNamesOrUnits(inputSource).getWeathertype());
        assertEquals(namings.getVisibility(), instance.readNamesOrUnits(inputSource).getVisibility());
        assertEquals(namings.getMint(), instance.readNamesOrUnits(inputSource).getMint());
        assertEquals(namings.getPresipсover(), instance.readNamesOrUnits(inputSource).getPresipсover());
    }

    @SneakyThrows
    @Test
    void readLocation_Success() {
        String inputSource = FileUtils.readFileToString(
                new File("src/test/resources/jsonparsing/response.json"),
                StandardCharsets.UTF_8);

        LocationDetailsData location = new LocationDetailsData();
        location.setAddress("Paris, Île-de-France, France");
        location.setLongitude(2.34141);
        location.setLatitude(48.8572);
        location.setTz("Europe/Paris");

        assertEquals(location.getAddress(), instance.readLocationDetails(inputSource, "paris").getAddress());
        assertEquals(location.getLongitude(), instance.readLocationDetails(inputSource, "paris").getLongitude());
        assertEquals(location.getLatitude(), instance.readLocationDetails(inputSource, "paris").getLatitude());
        assertEquals(location.getTz(), instance.readLocationDetails(inputSource, "paris").getTz());
    }
}
