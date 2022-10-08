package com.knubisoft.weatherdata;

import com.knubisoft.weatherdata.controller.ChartsController;
import com.knubisoft.weatherdata.handler.exception.EqualsDatesException;
import com.knubisoft.weatherdata.handler.exception.IncorrectPeriodException;
import com.knubisoft.weatherdata.handler.exception.IncorrectStartDateTimeException;
import com.knubisoft.weatherdata.requestdto.HistoricDataParams;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Validated
@SpringBootTest
public class ChartControllerTest {

    ChartsController instance = new ChartsController();

    @Test
    void buildHumidityChartOfHistoricData_HttpStatus_OK() {
        @Valid
        HistoricDataParams historicDataParams1 = new HistoricDataParams(
                "2019-01-01", "paris", "2019-01-03");
        @Valid
        HistoricDataParams historicDataParams2 = new HistoricDataParams(
                "2019-01-01", "csfhgxj-2", "2019-01-03");
        @Valid
        HistoricDataParams historicDataParams3 = new HistoricDataParams(
                "2022-09-", "toronto", "2022-09-15");
        @Valid
        HistoricDataParams historicDataParams4 = new HistoricDataParams(
                null, "paris", "2022-09-10");
        @Valid
        HistoricDataParams historicDataParams5 = new HistoricDataParams(
                "2022-09-05", "paris", "");
        @Valid
        HistoricDataParams historicDataParams6 = new HistoricDataParams(
                "2022-09-05", "", "2022-09-10");

        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricHumidityChanges(historicDataParams1).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricHumidityChanges(historicDataParams2).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricHumidityChanges(historicDataParams3).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricHumidityChanges(historicDataParams4).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricHumidityChanges(historicDataParams5).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricHumidityChanges(historicDataParams6).getStatusCode());
    }

    @Test
    void buildHumidityChartOfHistoricData_PeriodExceptions() {
        @Valid
        HistoricDataParams historicDataParams1 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-05");
        @Valid
        HistoricDataParams historicDataParams2 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-01");
        @Valid
        HistoricDataParams historicDataParams3 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-15");

        assertThrows(EqualsDatesException.class, () -> instance.buildChartOfHistoricHumidityChanges(historicDataParams1));
        assertThrows(IncorrectStartDateTimeException.class, () -> instance.buildChartOfHistoricHumidityChanges(historicDataParams2));
        assertThrows(IncorrectPeriodException.class, () -> instance.buildChartOfHistoricHumidityChanges(historicDataParams3));
    }

    @Test
    void buildPrecipitationChartOfHistoricData_HttpStatus_OK() {
        @Valid
        HistoricDataParams historicDataParams1 = new HistoricDataParams(
                "2019-01-01", "paris", "2019-01-03");
        @Valid
        HistoricDataParams historicDataParams2 = new HistoricDataParams(
                "2019-01-01", "csfhgxj-2", "2019-01-03");
        @Valid
        HistoricDataParams historicDataParams3 = new HistoricDataParams(
                "2022-09-", "toronto", "2022-09-15");
        @Valid
        HistoricDataParams historicDataParams4 = new HistoricDataParams(
                null, "paris", "2022-09-10");
        @Valid
        HistoricDataParams historicDataParams5 = new HistoricDataParams(
                "2022-09-05", "paris", "");
        @Valid
        HistoricDataParams historicDataParams6 = new HistoricDataParams(
                "2022-09-05", "", "2022-09-10");

        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricPrecipitationChanges(historicDataParams1).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricPrecipitationChanges(historicDataParams2).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricPrecipitationChanges(historicDataParams3).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricPrecipitationChanges(historicDataParams4).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricPrecipitationChanges(historicDataParams5).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricPrecipitationChanges(historicDataParams6).getStatusCode());
    }

    @Test
    void buildPrecipitationChartOfHistoricData_PeriodExceptions() {
        @Valid
        HistoricDataParams historicDataParams1 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-05");
        @Valid
        HistoricDataParams historicDataParams2 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-01");
        @Valid
        HistoricDataParams historicDataParams3 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-15");

        assertThrows(EqualsDatesException.class, () -> instance.buildChartOfHistoricPrecipitationChanges(historicDataParams1));
        assertThrows(IncorrectStartDateTimeException.class, () -> instance.buildChartOfHistoricPrecipitationChanges(historicDataParams2));
        assertThrows(IncorrectPeriodException.class, () -> instance.buildChartOfHistoricPrecipitationChanges(historicDataParams3));
    }

    @Test
    void buildTemperatureChartOfHistoricData_HttpStatus_OK() {
        @Valid
        HistoricDataParams historicDataParams1 = new HistoricDataParams(
                "2019-01-01", "paris", "2019-01-03");
        @Valid
        HistoricDataParams historicDataParams2 = new HistoricDataParams(
                "2019-01-01", "csfhgxj-2", "2019-01-03");
        @Valid
        HistoricDataParams historicDataParams3 = new HistoricDataParams(
                "2022-09-", "toronto", "2022-09-15");
        @Valid
        HistoricDataParams historicDataParams4 = new HistoricDataParams(
                null, "paris", "2022-09-10");
        @Valid
        HistoricDataParams historicDataParams5 = new HistoricDataParams(
                "2022-09-05", "paris", "");
        @Valid
        HistoricDataParams historicDataParams6 = new HistoricDataParams(
                "2022-09-05", "", "2022-09-10");

        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricTemperatureChanges(historicDataParams1).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricTemperatureChanges(historicDataParams2).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricTemperatureChanges(historicDataParams3).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricTemperatureChanges(historicDataParams4).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricTemperatureChanges(historicDataParams5).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildChartOfHistoricTemperatureChanges(historicDataParams6).getStatusCode());
    }

    @Test
    void buildTemperatureChartOfHistoricData_PeriodExceptions() {
        @Valid
        HistoricDataParams historicDataParams1 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-05");
        @Valid
        HistoricDataParams historicDataParams2 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-01");
        @Valid
        HistoricDataParams historicDataParams3 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-15");

        assertThrows(EqualsDatesException.class, () -> instance.buildChartOfHistoricTemperatureChanges(historicDataParams1));
        assertThrows(IncorrectStartDateTimeException.class, () -> instance.buildChartOfHistoricTemperatureChanges(historicDataParams2));
        assertThrows(IncorrectPeriodException.class, () -> instance.buildChartOfHistoricTemperatureChanges(historicDataParams3));
    }
}
