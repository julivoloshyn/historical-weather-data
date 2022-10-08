package com.knubisoft.weatherdata;

import com.knubisoft.weatherdata.parser.jsontotable.ParserToTable;
import com.knubisoft.weatherdata.table.tabledto.WeatherValues;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserJsonToTable {

    ParserToTable instance = new ParserToTable();

    @SneakyThrows
    @Test
    void readValues_Success() {
        String inputSource = FileUtils.readFileToString(
                new File("src/test/resources/jsonparsing/response.json"),
                StandardCharsets.UTF_8);

        WeatherValues values1 = new WeatherValues();
        values1.setMaxt(8.7);
        values1.setMint(7.4);
        values1.setHumidity(83.23);
        values1.setConditions("Rain, Partially cloudy");
        values1.setWeathertype("Drizzle, Mist, Light Drizzle, Rain, Light Drizzle/Rain, Light Rain, Sky Coverage Decreasing, Sky Unchanged");
        values1.setWspd(15.1);
        values1.setPresipсover(8.33);
        values1.setSolarradiation(16.6);
        values1.setVisibility(18.1);

        WeatherValues values2 = new WeatherValues();
        values2.setMaxt(7.9);
        values2.setMint(5);
        values2.setHumidity(71.97);
        values2.setConditions("Rain, Partially cloudy");
        values2.setWeathertype("Drizzle, Sky Coverage Decreasing");
        values2.setWspd(19.9);
        values2.setPresipсover(0);
        values2.setSolarradiation(36.7);
        values2.setVisibility(24.1);

        WeatherValues values3 = new WeatherValues();
        values3.setMaxt(6.3);
        values3.setMint(3.9);
        values3.setHumidity(78.15);
        values3.setConditions("Partially cloudy");
        values3.setWeathertype("");
        values3.setWspd(15.1);
        values3.setPresipсover(0);
        values3.setSolarradiation(40.4);
        values3.setVisibility(22.8);

        List<WeatherValues> list = new ArrayList<>();
        list.add(values1);
        list.add(values2);
        list.add(values3);

        assertEquals(list.get(0).getConditions(), instance.readAllValues("paris", inputSource, WeatherValues.class).get(0).getConditions());
        assertEquals(list.get(1).getMaxt(), instance.readAllValues("paris", inputSource, WeatherValues.class).get(1).getMaxt());
        assertEquals(list.get(0).getMint(), instance.readAllValues("paris", inputSource, WeatherValues.class).get(0).getMint());
        assertEquals(list.get(2).getPresipсover(), instance.readAllValues("paris", inputSource, WeatherValues.class).get(2).getPresipсover());
        assertEquals(list.get(1).getVisibility(), instance.readAllValues("paris", inputSource, WeatherValues.class).get(1).getVisibility());
        assertEquals(list.get(0).getWeathertype(), instance.readAllValues("paris", inputSource, WeatherValues.class).get(0).getWeathertype());
    }
}
