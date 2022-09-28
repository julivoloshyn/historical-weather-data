package com.knubisoft.weatherdata.chartbuilder.dataprocessor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GivenPeriod {

    public List<LocalDate> createListOfDates(String startDateTime, String endDateTime) {

        List<LocalDate> period = new ArrayList<>();

        LocalDate currentDate = LocalDate.parse(startDateTime.substring(0, 10));
        LocalDate endDate = LocalDate.parse(endDateTime.substring(0, 10));

        while (!currentDate.equals(endDate.plusDays(1))){
            period.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return period;
    }
}
