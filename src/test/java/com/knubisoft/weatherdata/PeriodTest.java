package com.knubisoft.weatherdata;

import com.knubisoft.weatherdata.dataprocessor.PeriodProcessor;
import com.knubisoft.weatherdata.handler.exception.EqualsDatesException;
import com.knubisoft.weatherdata.handler.exception.IncorrectPeriodException;
import com.knubisoft.weatherdata.handler.exception.IncorrectStartDateTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PeriodTest {

    PeriodProcessor instance = new PeriodProcessor();

    @Test
    void createListOfDates_Success(){
        List<LocalDate> dateList = List.of(
                LocalDate.parse("2022-01-01"),
                LocalDate.parse("2022-01-02"),
                LocalDate.parse("2022-01-03"),
                LocalDate.parse("2022-01-04")
        );

        assertEquals(dateList, instance.createListOfDates("2022-01-01", "2022-01-04"));
    }

    @Test
    void createListOfDates_Fail(){
        assertThrows(IncorrectStartDateTimeException.class, () -> instance.createListOfDates("2022-01-08", "2022-01-04"));
        assertThrows(IncorrectPeriodException.class, () -> instance.createListOfDates("2022-01-08", "2022-01-20"));
        assertThrows(EqualsDatesException.class, () -> instance.createListOfDates("2022-01-08", "2022-01-08"));
    }

    @Test
    void validate_Fail(){
        assertThrows(DateTimeParseException.class, () -> instance.createListOfDates("2022-01-", "2022-01-04"));
        assertThrows(DateTimeParseException.class, () -> instance.createListOfDates("2022-01-01", "202-04"));

        assertThrows(NullPointerException.class, () -> instance.createListOfDates(null, "2022-01-04"));
        assertThrows(NullPointerException.class, () -> instance.createListOfDates("2022-01-04", null));
    }

}
