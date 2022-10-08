package com.knubisoft.weatherdata;

import com.knubisoft.weatherdata.controller.TableController;
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
public class TableControllerTest {

    TableController instance = new TableController();

    @Test
    void buildTableOfHistoricData_HttpStatus_OK() {
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

        assertEquals(HttpStatus.OK, instance.buildTableOfHistoricData(historicDataParams1).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildTableOfHistoricData(historicDataParams2).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildTableOfHistoricData(historicDataParams3).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildTableOfHistoricData(historicDataParams4).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildTableOfHistoricData(historicDataParams5).getStatusCode());
        assertEquals(HttpStatus.OK, instance.buildTableOfHistoricData(historicDataParams6).getStatusCode());
    }

    @Test
    void buildTableOfHistoricData_PeriodExceptions() {
        @Valid
        HistoricDataParams historicDataParams1 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-05");
        @Valid
        HistoricDataParams historicDataParams2 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-01");
        @Valid
        HistoricDataParams historicDataParams3 = new HistoricDataParams(
                "2022-09-05", "paris", "2022-09-15");

        assertThrows(EqualsDatesException.class, () -> instance.buildTableOfHistoricData(historicDataParams1));
        assertThrows(IncorrectStartDateTimeException.class, () -> instance.buildTableOfHistoricData(historicDataParams2));
        assertThrows(IncorrectPeriodException.class, () -> instance.buildTableOfHistoricData(historicDataParams3));
    }


}
