package com.knubisoft.weatherdata.dataprocessor;

import com.knubisoft.weatherdata.handler.exception.EqualsDatesException;
import com.knubisoft.weatherdata.handler.exception.IncorrectPeriodException;
import com.knubisoft.weatherdata.handler.exception.IncorrectStartDateTimeException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PeriodProcessor {

    /**
     * Creates a list of dates between startDateTime and endDateTime.
     *
     * @param startDateTime Date time from request.
     * @param endDateTime Date time from request.
     * @return List of dates.
     */
    public List<LocalDate> createListOfDates(String startDateTime, String endDateTime) {
        LocalDate currentDate = LocalDate.parse(startDateTime);
        LocalDate endDate = LocalDate.parse(endDateTime);

        checkForCorrectPeriod(currentDate, endDate);

        List<LocalDate> period = new ArrayList<>();
        while (!currentDate.equals(endDate.plusDays(1))){
            period.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return period;
    }

    /**
     * Checks if a period is correct.
     *
     * @param currentDate Start date time.
     * @param endDate End date time.
     */
    private void checkForCorrectPeriod(LocalDate currentDate, LocalDate endDate) {
        long period = ChronoUnit.DAYS.between(currentDate, endDate);

        if(currentDate.isAfter(endDate)){
            throw new IncorrectStartDateTimeException();
        }

        if(period > 6){
            throw new IncorrectPeriodException();
        }

        if(period == 0){
            throw new EqualsDatesException();
        }
    }
}
